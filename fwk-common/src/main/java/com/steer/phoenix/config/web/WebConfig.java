package com.steer.phoenix.config.web;

import com.steer.phoenix.core.inteceptor.AttributeSetInteceptor;
import com.steer.phoenix.core.inteceptor.RestApiInteceptor;
import com.steer.phoenix.core.listener.ConfigListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.steer.phoenix.constants.Constants.NONE_PERMISSION_RES;

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
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestApiInteceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new AttributeSetInteceptor()).excludePathPatterns(NONE_PERMISSION_RES).addPathPatterns("/**");
    }

    /**
     * ConfigListener注册
     */
    @Bean
    public ServletListenerRegistrationBean<ConfigListener> configListenerRegistration() {
        return new ServletListenerRegistrationBean<>(new ConfigListener());
    }
}
