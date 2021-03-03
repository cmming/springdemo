/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.converter;

import com.example.springdemo.dao.User;
import net.sf.json.JSONObject;
import org.springframework.core.convert.converter.Converter;

/**
 * 将JSONObject转成User对象 .
 *
 * @version 2.0.0 2020-12-22 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
public class TestConverter {

    public static User convert(JSONObject jsonObject) {
        User user1 = new User();
        user1.setName(jsonObject.getString("name"));
        user1.setAge((Integer) jsonObject.get("age"));
        return user1;
    }
}
