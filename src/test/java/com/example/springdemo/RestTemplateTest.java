/*
 * Copyright 2000-2020 YGSoft.Inc All Rights Reserved.
 */
package com.example.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 类的描述 .
 *
 * @version 1.0.0  <br>
 * @author: chenming <br>
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RestTemplateTest {

    @Test
    public void demo() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("ctx","{\"loginContext\":{\"jcls\":\"com.ygsoft.ecp.core.framework.internal.context.LoginContext\"},\"jcls\":\"com.ygsoft.ecp.core.framework.internal.context.EcpDataContext\"}");
        map.add("classId", "gldx");
        map.add("typeId", "5113");
        map.add("dxid","1136987");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        log.info("远程调用传递参数:" + formEntity);
        final String uploadRoomInfoUrl = "https://gris.ygsoft.com/grm/ecp/RemoteServiceServlet?callName=com.ygsoft.ecp.app.gldx.service.context.IGldxQueryContext.findByDxid(ctx,%20classId,%20typeId,%20dxid)";
        String result = restTemplate.postForEntity(uploadRoomInfoUrl, formEntity, String.class).getBody();
        log.info("远程调用返回结果:" + result);
    }
}
