package com.example.springdemo.service.impl;

import com.example.springdemo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 10:51 2020-10-19
 **/
@Service
@Slf4j
public class MailServiceImpl implements MailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            log.info("邮件已发送成功!,发件地址：{}，收件地址：{}", from, to);
        } catch (Exception e) {
            log.error("发送邮件时发生异常!", e);
        }

    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            javaMailSender.send(message);
            log.info("邮件已发送成功!,发件地址：{}，收件地址：{}", from, to);
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常!", e);
        }

    }

    /**
     * TODO thymeleaf 语法不是太熟悉
     * @param to 发送人
     * @param subject 邮件主题
     * @param content 邮件变量
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        try {
            Context context = new Context();
            context.setVariable("username", content);
            String templateContent = templateEngine.process("emailTemplate", context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
