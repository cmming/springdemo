/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.config;

import com.example.springdemo.handler.MyRestErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 执行HTTP请求的同步阻塞式的客户端 .
 * 将RestTemplate配置初始化为一个Bean
 * http://static.kancloud.cn/hanxt/resttemplate
 * https://zhuanlan.zhihu.com/p/89378056
 * 1、实例配置
 * 2、message转换器
 * 3、拦截器
 * 4、自定义异常处理
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@Configuration
public class ContextConfig {

    @Value("${restTemplate.connection.timeout}")
    private int restTemplateConnectionTimeout;

    @Value("${restTemplate.read.timeout}")
    private int restTemplateReadTimeout;

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory reqFactory= new SimpleClientHttpRequestFactory();
        // 连接时间
        reqFactory.setConnectTimeout(restTemplateConnectionTimeout);
        // 读取数据时间
        reqFactory.setReadTimeout(restTemplateReadTimeout);
        return reqFactory;
    }

    /**
     * 默认使用 JDK 自带的HttpURLConnection作为底层实现
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyRestErrorHandler());
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory());
        return restTemplate;
    }

    /**
     * OkHttp 作为底层实现
     * @return
     */
    @Bean("OKHttp3")
    public RestTemplate OKHttp3RestTemplate(){
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        return restTemplate;
    }
}
