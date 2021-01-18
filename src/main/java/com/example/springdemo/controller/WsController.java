/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.springdemo.VO.ResultVO;
import com.example.springdemo.common.WebSocketConst;
import com.example.springdemo.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 类的描述 .
 *
 * @version 2.0.0 2021-01-18 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/ws/")
public class WsController {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public void setSimpMessagingTemplate(final SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * 当用户访问接口 /ws/ontToOne 就会接收到WebSocket消息
     * @return
     */
    @GetMapping("ontToOne")
    public ResultVO ontToOne(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("a", "a");
        simpMessagingTemplate.convertAndSend(WebSocketConst.WS_ONE_TO_ONE, JSONUtil.toJsonStr(jsonObject));
        return ResultVOUtil.success();
    }
}
