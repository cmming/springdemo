/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 类的描述 .
 * TODO 文件上传传、下载、流上传
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void demo(){
        String url = "https://gris.ygsoft.com/grm/ecp/RemoteServiceServlet";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        LinkedMultiValueMap postParameters = new LinkedMultiValueMap<>();
        postParameters.set("ctx","{\"loginContext\":{\"jcls\":\"com.ygsoft.ecp.core.framework.internal.context.LoginContext\"},\"jcls\":\"com.ygsoft.ecp.core.framework.internal.context.EcpDataContext\"}");
        postParameters.set("classId", "gldx");
        postParameters.set("typeId", "5113");
        postParameters.set("dxid","1136987");
        postParameters.set("callName","com.ygsoft.ecp.app.gldx.service.context.IGldxQueryContext.findByDxid(ctx,classId,typeId,dxid)");
        HttpEntity request = new HttpEntity(postParameters,headers);
        String result = restTemplate.postForObject(url, request, String.class);
        JSONObject resultObj = JSONObject.fromObject(result);
        System.out.println(result);
        System.out.println(resultObj);
    }

    @Test
    public void testHttpUtil() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("ctx","{\"loginContext\":{\"jcls\":\"com.ygsoft.ecp.core.framework.internal.context.LoginContext\"},\"jcls\":\"com.ygsoft.ecp.core.framework.internal.context.EcpDataContext\"}");
        paramMap.put("classId", "gldx");
        paramMap.put("typeId", "5113");
        paramMap.put("dxid","1136987");
        paramMap.put("callName","com.ygsoft.ecp.app.gldx.service.context.IGldxQueryContext.findByDxid(ctx,classId,typeId,dxid)");
        String url = "https://gris.ygsoft.com/grm/ecp/RemoteServiceServlet";
        String result = HttpUtil.post(url, paramMap);
        System.out.println(result);
    }

    @Test
    public void testSimple() {
        String url = "http://jsonplaceholder.typicode.com/posts/1";
        String str = restTemplate.getForObject(url, String.class);
        System.out.println(str);
    }

    /**
     * getForEntity比getForObject可以获取到响应头里面的信息 .
     */
    @Test
    public void testEntityPoJo() {
        String url = "http://jsonplaceholder.typicode.com/posts/5";
        ResponseEntity<PostDTO> responseEntity
                = restTemplate.getForEntity(url, PostDTO.class);
        PostDTO postDTO = responseEntity.getBody(); // 获取响应体
        System.out.println("HTTP 响应body：" + postDTO.toString());


        //以下是getForEntity比getForObject多出来的内容
        HttpStatus statusCode = responseEntity.getStatusCode(); // 获取响应码
        int statusCodeValue = responseEntity.getStatusCodeValue(); // 获取响应码值
        HttpHeaders headers = responseEntity.getHeaders(); // 获取响应头

        System.out.println("HTTP 响应状态：" + statusCode);
        System.out.println("HTTP 响应状态码：" + statusCodeValue);
        System.out.println("HTTP Headers信息：" + headers);
    }

    /**
     * 模拟发送json数据 .
     */
    @Test
    public void testPostSimple()  {
        // 请求地址
        String url = "http://jsonplaceholder.typicode.com/posts";

        // 要发送的数据对象
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(110);
        postDTO.setTitle("zimug 发布文章");
        postDTO.setBody("zimug 发布文章 测试内容");

        // 发送post请求，并输出结果
        PostDTO result = restTemplate.postForObject(url, postDTO, PostDTO.class);
        System.out.println(result);
    }

    /**
     * 模拟表单发送数据 .
     */
    @Test
    public void testPostForm() {
        // 请求地址
        String url = "http://jsonplaceholder.typicode.com/posts";

        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("title", "zimug 发布文章第二篇");
        map.add("body", "zimug 发布文章第二篇 测试内容");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<MultiValueMap<String, String>>(map, headers);

        // 发送post请求，并打印结果，以String类型接收响应结果JSON字符串
        String result = restTemplate.postForObject(url, request, String.class);
        System.out.println(result);
    }
}
