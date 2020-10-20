package com.example.springdemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: chenming
 * @Description: 模拟测试拦截器
 * @Date: Create in 9:11 2020-10-20
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * controller 逻辑执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("开始登陆拦截器");
            log.info("拦截的Controller：{}", handlerMethod.getBean().getClass().getName());
            log.info("拦截的方法：{}", handlerMethod.getMethod().getName());
        }
        return true;
    }

    /**
     * controller逻辑执行完毕但是视图层还未解析之前 .
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle....");
//        Map<String,Object> map=modelAndView.getModel();
//        map.put("msg","postHandle add msg");
    }

    //Controller逻辑和视图解析器执行完毕
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion....");
    }
}
