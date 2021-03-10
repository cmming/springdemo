/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.config;

import com.example.springdemo.resolver.DemoUserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 推荐使用实现WebMvcConfigurer替代扩展WebMvcConfigurationSupport的做法来配置spring .
 *
 * @version 2.0.0 2021-03-02 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@Configuration
//@EnableWebMvc
public class DemoUserConfig implements WebMvcConfigurer {
    @Autowired
    private DemoUserArgumentResolver demoUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(demoUserArgumentResolver);
    }
}
