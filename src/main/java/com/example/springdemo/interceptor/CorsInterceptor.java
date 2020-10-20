package com.example.springdemo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: chenming
 * @Description:跨域拦截器
 * @Date: Create in 9:39 2020-10-20
 **/
public class CorsInterceptor implements HandlerInterceptor {
    
    private Logger log = LoggerFactory.getLogger(CorsInterceptor.class);

    // controller 逻辑执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");

        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");

        String method= request.getMethod();

        if (method.equals("OPTIONS")){
            response.setStatus(200);
            return false;
        }
        log.info("开始跨域拦截器的执行....");

        return true;
    }

    // controller逻辑执行完毕但是视图层还未解析之前 .
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    // controller逻辑和视图解析器执行完毕 .
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
