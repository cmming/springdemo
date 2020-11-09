package com.example.springdemo.interceptor;

import com.example.springdemo.enums.ResultEnum;
import com.example.springdemo.exception.DemoException;
import com.example.springdemo.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: chenming
 * @Description: 特使字符拦截器 。
 * @Date: Create in 11:47 2020-10-28
 **/
@Slf4j
public class SpecialSingleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断请求中是否包含特殊字符
        Map<String, String[]> params = request.getParameterMap();
        Enumeration<String> paramsName = request.getParameterNames();
        if (paramsName.hasMoreElements()) {
            for ( String paramItemKey : params.keySet()) {
                 String paramString = URLEncoder.encode(StringUtils.join(params.get(paramItemKey), ""), "UTF-8").toUpperCase();
                // 处理空 URLEncoder后会变成加号
                // paramString = paramString.replaceAll("\\+", "%20");
//                String paramString = StringUtils.join(params.get(paramItemKey), "");
                // 21个 反斜杠会有问题
                boolean isContainSpecialSingle = Pattern.matches(".*(%2B|%20|%2F|%3F|%25|%23|%26|%3D|%27|%22|%28|%29|%2A|%2D|%3C|%3E|%5C|%5E|%3B|%3A|%21|\\*|-|>|<|&|#|\\+|\\(|\\)).*", paramString);
                log.error("请求参数包含特殊字符，请求参数为：{}", JsonUtils.toJson(paramString));
                log.error("请求参数包含特殊字符，请求参数为：{}", JsonUtils.toJson(params));
                if (isContainSpecialSingle) {
                    log.error("请求参数包含特殊字符，请求参数为：{}", JsonUtils.toJson(params));
                    throw new DemoException(ResultEnum.PARAMS_CONTAIN_SPECIAL_SINGLE.getCode(), ResultEnum.PARAMS_CONTAIN_SPECIAL_SINGLE.getMessage());
                }
            }
        }
        return true;
    }
}
