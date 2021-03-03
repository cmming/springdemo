/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.controller;

import com.example.springdemo.converter.TestConverter;
import com.example.springdemo.dao.User;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取请求参数的常见方式 .
 *
 * @version 2.0.0 2020-12-22 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/params/")
public class GetParamsController {

    @PostMapping("jsonObject")
    public void getJSONObject(@RequestBody JSONObject jsonObject) {
        User user = TestConverter.convert(jsonObject);
        System.out.println(user.toString());
    }
}
