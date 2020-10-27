package com.example.springdemo.service.impl;

import com.example.springdemo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSend() {
        mailService.sendSimpleMail("13037125104@163.com","test","test内容");
    }

    @Test
    public void testSendAttachmentsMail() throws FileNotFoundException {
        String path = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static\\1.log").getPath();
        mailService.sendAttachmentsMail("13037125104@163.com", "文件","文件", path);
    }

    @Test
    public void testSendHtmlMail() {
        mailService.sendHtmlMail("13037125104@163.com", "邮件html模板测试","chmi");
    }
}