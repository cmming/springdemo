/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo;

import cn.hutool.core.io.file.FileWriter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String AZ(int num){
        char sl = (char) (num + (int) 'a');
        String tcMsg = "" + sl;
        return tcMsg;
    }

//    @Test
    public String testL1(int pageNum) {
        // 最大只支持100
//        int pageNum = 0;
        String url = "https://ygjy.ismartwork.cn/cwjy/mapp/restful/training/getPracticeTopicList?pageNo="
        + pageNum +"&pageSize=100&categoryId=ad7ece8409fa49f0a63558bccd8e1380&trainingType=1";
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        cookies.add("ecpDataContext.tokenId=5666d5a1f9ca42a18cd12ce334af09ee; Path=/; Domain=.ygjy.ismartwork.cn; Expires=Sat, 25 Dec 2021 07:00:20 GMT;");
        headers.put(HttpHeaders.COOKIE,cookies);
        HttpEntity request = new HttpEntity(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        JSONObject resultObj = JSONObject.fromObject(result.getBody());
        List<JSONObject> res = (List<JSONObject>) resultObj.get("body");
        String pageHtmlString = "";
        for (int i = 0; i < res.size(); i++) {
            String topicContent =  "<p>" + (pageNum*100 + i+1) + "、" + (String) res.get(i).get("topicContent") + "</p>";
            List<JSONObject> topicOptionVos = (List<JSONObject>) res.get(i).get("topicOptionVos");
            List<String> answer = (List<String>) res.get(i).get("lastOptionIds");
            // 计算正确答案
            String answerString = "正确答案是：";
            String options = "";
            if (topicOptionVos.size() > 0) {
                for ( int j = 0; j < topicOptionVos.size(); j++) {
                    Integer optionValue = (Integer) topicOptionVos.get(j).get("optionValue");
                    if (optionValue == 1) {
                        answerString = answerString + "<span style='color:green'>" + AZ(j) + "</span>";
                        options += "<p style='color:red;font-size:16px;'>" + AZ(j) + topicOptionVos.get(j).get("optionContent") + "</p><br>";
                    } else {
                        options += "<p>" + AZ(j) + topicOptionVos.get(j).get("optionContent") + "</p><br>";
                    }
                }
            } else {
                String result2 = (String) res.get(i).get("result");
                if (result2.equals("1")) {
                    answerString = answerString + "<span style='color:green'>正确</span>";
                    options += "<p style='color:red;font-size:16px;'>" + AZ(0) + "正确" + "</p><br>";
                    options += "<p>" + AZ(1) + "错误" + "</p><br>";
                } else {
                    answerString = answerString + "<span style='color:green'>错误</span>";
                    options += "<p>" + AZ(0) + "正确" + "</p><br>";
                    options += "<p style='color:red;font-size:16px;'>" + AZ(1) + "错误" + "</p><br>";
                }
            }

            // 一个题目
            pageHtmlString = pageHtmlString + topicContent + answerString + options;
        }
        System.out.println(pageHtmlString);
        return pageHtmlString;
    }

    // 261 bug
    @Test
    public void testL1Page() {
        String result = "<style>*{padding: 0;margin: 0;}</style>";
//        String result = "";
        for (int i = 0; i < 5; i++) {
            result = result + testL1(i);
        }
        FileWriter writer = new FileWriter("UX-L1.html");
        writer.write(result);
    }
}
