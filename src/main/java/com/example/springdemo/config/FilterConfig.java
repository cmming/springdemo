package com.example.springdemo.config;

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
    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 设置自定义拦截器
        registration.setFilter(new URLFilter());
        // 设置拦截器匹配的url
        registration.addUrlPatterns("/*");
        // 设置拦截器名称
        registration.setName("urlFilter");
        // 设置自定义拦截器的顺序
        registration.setOrder(1);
        return registration;
    }
}
