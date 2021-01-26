/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.handler;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 类的描述 .
 *
 * @version 2.0.0 2021-01-20 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@ControllerAdvice
public class TestModelViewHandler {
    @ModelAttribute(value = "test")
    public String message(final HttpServletRequest request) {

        return "test";
    }

    @ModelAttribute(value = "test1")
    public String message1(final HttpServletRequest request) {

        return "test1";
    }
}
