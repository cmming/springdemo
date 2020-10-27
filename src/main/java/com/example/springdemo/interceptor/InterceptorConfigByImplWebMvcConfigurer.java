package com.example.springdemo.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: chenming
 * @Description: 配置拦截器（多个拦截器执行顺序按照先注册先执行的方式判断执行顺序） .
 * @Date: Create in 9:20 2020-10-20
 **/
@Configuration
public class InterceptorConfigByImplWebMvcConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public CorsInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }

    /**
     * 将拦截器注册到spring的上下文中 .
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**").excludePathPatterns("/*.html");
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/*.html");
    }
}
