package com.example.springdemo.config;

import com.example.springdemo.filter.LoginFilter;
import com.example.springdemo.filter.URLFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenming
 * @Description: 利用FilterRegistrationBean完成配置注入 .
 * @Date: Create in 17:11 2020-10-19
 **/
@Configuration
public class FilterConfig {

    /**
     * URLFilter过滤器配置 .
     * @return
     */
    @Bean
    public FilterRegistrationBean registerUrlFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 设置自定义拦截器
        registration.setFilter(new URLFilter());
        // 设置拦截器匹配的url
        registration.addUrlPatterns("/*");
        // 设置拦截器名称
        registration.setName("urlFilter");
        // 设置自定义拦截器的顺序(order越小越先执行)
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean registerLoginFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 设置自定义拦截器
        registration.setFilter(new LoginFilter());
        // 设置拦截器匹配的url
        registration.addUrlPatterns("/*");
        // 设置拦截器名称
        registration.setName("loginFilter");
        // 设置自定义拦截器的顺序
        registration.setOrder(2);
        return registration;
    }
}
