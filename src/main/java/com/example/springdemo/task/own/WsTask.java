/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.task.own;

import com.example.springdemo.common.WebSocketConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * WebSocket定时任务 .
 *
 * @version 2.0.0 2021-01-18 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@Component
public class WsTask {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public void setSimpMessagingTemplate(final SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 定时推送消息
     */
    @Scheduled(fixedRate = 1000)
    public void callback() {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpMessagingTemplate.convertAndSend(WebSocketConst.WS_SCHEDULED, "定时推送消息时间: " + df.format(System.currentTimeMillis()));
    }
}
