/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo.resolver;

import com.example.springdemo.annotation.DemoUser;
import com.example.springdemo.dao.User;
import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.DemoException;
import com.example.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 类的描述 .
 *
 * @version 2.0.0 2021-03-01 <br>
 * @author: ChenMing <br>
 * @since JDK 1.8
 */
@Component
public class DemoUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(DemoUser.class);
    }

    @Override
    public User resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        User result = null;
        Annotation[] methodAnnotations = methodParameter.getParameterAnnotations();
        for ( Annotation methodAnnotation : methodAnnotations) {
            if (methodAnnotation instanceof DemoUser) {
                // DemoUser demoUser = (DemoUser) methodAnnotation;
                HttpServletRequest httpServletRequest=nativeWebRequest.getNativeRequest(HttpServletRequest.class);
                Integer id = Integer.valueOf(httpServletRequest.getParameter("id"));
                if (id == null) {
                    throw new DemoException(ResultEnum.PARAMS_ERROR);
                }
                result = userRepository.findById(id).get();
                return result;
            }
        }
        return null;
    }
}
