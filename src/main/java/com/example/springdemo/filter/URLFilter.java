package com.example.springdemo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: chenming
 * @Description: filter 拦截所有访问项目的URL
 * @Date: Create in 16:33 2020-10-19
 **/
// @WebFilter(urlPatterns = "/*", filterName = "urlFilter")
@Slf4j
public class URLFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println("URLFilter生效");
        log.info("访问地址:" + requestURI);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
