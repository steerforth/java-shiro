package com.steer.phoenix.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //本应用
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    /**
     * 增加对rest api鉴权的spring mvc拦截器
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RestApiInteceptor()).addPathPatterns("/gunsApi/**");
//        registry.addInterceptor(new AttributeSetInteceptor()).excludePathPatterns(NONE_PERMISSION_RES).addPathPatterns("/**");
//    }
}
