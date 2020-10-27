package com.example.springdemo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: chenming
 * @Description: 判断用户是否登录
 * @Date: Create in 23:19 2020/10/19
 */
@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Object userName = httpServletRequest.getSession().getAttribute("userName");
        // 纯粹是为了测试
        if (userName == null) {
            // httpServletRequest.setAttribute("error", "非法进入");
            log.info("LoginFilter用户没有登录");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
