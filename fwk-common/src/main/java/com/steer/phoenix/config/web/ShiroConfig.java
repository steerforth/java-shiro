package com.steer.phoenix.config.web;

import com.steer.phoenix.core.inteceptor.JwtFilter;
import com.steer.phoenix.core.inteceptor.UserFilter;
import com.steer.phoenix.core.shiro.ShiroDbRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.steer.phoenix.constants.Constants.NONE_PERMISSION_RES;

@Configuration
public class ShiroConfig {
    private Logger log  = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(CacheManager cacheShiroManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //1.自定义realm
        securityManager.setRealm(this.shiroDbRealm());
//        securityManager.setCacheManager(cacheShiroManager);
//        securityManager.setRememberMeManager(rememberMeManager);
//        securityManager.setSessionManager(sessionManager);
        //2.session管理
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-
         * StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        //3.缓存管理
        //TODO 自定义缓存实现,使用redis
        securityManager.setCacheManager(cacheShiroManager);
        return securityManager;
    }

//    /**
//     * spring session管理器（多机环境）
//     */
//    @Bean
//    @ConditionalOnProperty(prefix = "steer", name = "spring-session-open", havingValue = "true")
//    public ServletContainerSessionManager servletContainerSessionManager() {
//        return new ServletContainerSessionManager();
//    }
//
//    /**
//     * session管理器(单机环境)
//     */
//    @Bean
//    @ConditionalOnProperty(prefix = "steer", name = "spring-session-open", havingValue = "false")
//    public DefaultWebSessionManager defaultWebSessionManager(CacheManager cacheShiroManager, SteerProperties properties) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setCacheManager(cacheShiroManager);
//        sessionManager.setSessionValidationInterval(properties.getSessionValidationInterval() * 1000);
//        sessionManager.setGlobalSessionTimeout(properties.getSessionInvalidateTime() * 1000);
//        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
//        cookie.setName("shiroCookie");
//        cookie.setHttpOnly(true);
//        sessionManager.setSessionIdCookie(cookie);
//        return sessionManager;
//    }

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public CacheManager getCacheShiroManager(EhCacheManagerFactoryBean ehcache) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehcache.getObject());
        return ehCacheManager;
    }

    /**
     * 项目自定义的Realm
     */
    @Bean
    public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealm();
    }

    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
//        CookieRememberMeManager manager = new CookieRememberMeManager();
//        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAABBB=="));
//        manager.setCookie(rememberMeCookie);
//        return manager;
//    }

    /**
     * 记住密码Cookie
     */
//    @Bean
//    public SimpleCookie rememberMeCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
//        return simpleCookie;
//    }

    /**
     * Shiro的过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        /**
         * 默认的登陆访问url
         */
        shiroFilter.setLoginUrl("/login");
        /**
         * 登陆成功后跳转的url
         */
        shiroFilter.setSuccessUrl("/");
        /**
         * 没有权限跳转的url
         */
        shiroFilter.setUnauthorizedUrl("/global/error");

        /**
         * FIXME 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题)
         */
        HashMap<String, Filter> myFilters = new HashMap<>();
        myFilters.put("jwt", new JwtFilter());
        shiroFilter.setFilters(myFilters);

        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * user  验证通过或RememberMe登录的都可以
         *
         * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
         *
         * 顺序从上到下,优先级依次降低
         *
         * api开头的接口，走rest api鉴权，不走shiro鉴权
         *
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        for (String nonePermissionRe : NONE_PERMISSION_RES) {
            filterChainDefinitionMap.put(nonePermissionRe, "anon");
        }

        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/sys/getCheckCode", "anon"); //登录验证码接口排除
        filterChainDefinitionMap.put("/sys/login", "anon"); //登录接口排除
        filterChainDefinitionMap.put("/sys/mLogin", "anon"); //登录接口排除
        filterChainDefinitionMap.put("/sys/logout", "anon"); //登出接口排除
        filterChainDefinitionMap.put("/sys/getEncryptedString", "anon"); //获取加密串
        filterChainDefinitionMap.put("/sys/sms", "anon");//短信验证码
        filterChainDefinitionMap.put("/sys/phoneLogin", "anon");//手机登录
        filterChainDefinitionMap.put("/sys/user/checkOnlyUser", "anon");//校验用户是否存在
        filterChainDefinitionMap.put("/sys/user/register", "anon");//用户注册
        filterChainDefinitionMap.put("/sys/user/querySysUser", "anon");//根据手机号获取用户信息
        filterChainDefinitionMap.put("/sys/user/phoneVerification", "anon");//用户忘记密码验证手机号
        filterChainDefinitionMap.put("/sys/user/passwordChange", "anon");//用户更改密码
        filterChainDefinitionMap.put("/auth/2step-code", "anon");//登录验证码
        filterChainDefinitionMap.put("/sys/common/view/**", "anon");//图片预览不限制token
        filterChainDefinitionMap.put("/sys/common/download/**", "anon");//文件下载不限制token
        filterChainDefinitionMap.put("/sys/common/pdf/**", "anon");//pdf预览
        filterChainDefinitionMap.put("/generic/**", "anon");//pdf预览需要文件
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/**/*.js", "anon");
        filterChainDefinitionMap.put("/**/*.css", "anon");
        filterChainDefinitionMap.put("/**/*.html", "anon");
        filterChainDefinitionMap.put("/**/*.svg", "anon");
        filterChainDefinitionMap.put("/**/*.pdf", "anon");
        filterChainDefinitionMap.put("/**/*.jpg", "anon");
        filterChainDefinitionMap.put("/**/*.png", "anon");
        filterChainDefinitionMap.put("/**/*.ico", "anon");

        // update-begin--Author:sunjianlei Date:20190813 for：排除字体格式的后缀
        filterChainDefinitionMap.put("/**/*.ttf", "anon");
        filterChainDefinitionMap.put("/**/*.woff", "anon");
        // update-begin--Author:sunjianlei Date:20190813 for：排除字体格式的后缀

        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger**/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        //性能监控
        filterChainDefinitionMap.put("/actuator/metrics/**", "anon");
        filterChainDefinitionMap.put("/actuator/httptrace/**", "anon");
        filterChainDefinitionMap.put("/actuator/redis/**", "anon");

        //测试示例
        filterChainDefinitionMap.put("/test/jeecgDemo/html", "anon"); //模板页面
        filterChainDefinitionMap.put("/test/jeecgDemo/redis/**", "anon"); //redis测试

        //排除Online请求
        filterChainDefinitionMap.put("/auto/cgform/**", "anon");

        //websocket排除
        filterChainDefinitionMap.put("/websocket/**", "anon");

        //将/**放在最下面
        filterChainDefinitionMap.put("/**", "jwt");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

//    /**
//     * 在方法中 注入 securityManager,进行代理控制
//     */
//    @Bean
//    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
//        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
//        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//        bean.setArguments(securityManager);
//        return bean;
//    }

    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 下面的代码是添加注解支持
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
