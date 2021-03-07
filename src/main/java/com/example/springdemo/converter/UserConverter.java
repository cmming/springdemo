/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.converter;

import com.example.springdemo.dao.User;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.DemoException;
import com.example.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 根据用户的id，转换成用户对象 .
 *
 * @version 2.0.0 2021-03-03 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
public class UserConverter implements Converter<Integer, User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(Integer id) {
        User result = userRepository.findById(id).get();
        if (result == null) {
            throw new DemoException(ResultEnum.USER_NOT_FOUND);
        }
        return result;
    }
}
