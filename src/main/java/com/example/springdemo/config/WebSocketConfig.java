/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket配置 .
 *
 * @version 2.0.0 2021-01-18 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 添加这个Endpoint，这样在网页中就可以通过websocket连接上服务,也就是我们配置websocket的服务地址,并且可以指定是否使用socketjs
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个 /notification 端点，前端通过这个端点进行连接
        registry.addEndpoint("/notification")
                //解决跨域问题
                .setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置消息代理，哪种路径的消息会进行代理处理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //订阅Broker名称,/user代表点对点即发指定用户，/topic代表发布广播即群发
        // 点对点式增加一个/topic 消息代理
        registry.enableSimpleBroker("/topic");
    }
}
