package com.example.springdemo.service;

import javax.mail.MessagingException;

/**
 * @Author: chenming
 * @Description:
 * @Date: Create in 10:50 2020-10-19
 **/
public interface MailService {

    /**
     * 发送邮件 .
     * @param to 发件人
     * @param subject
     * @param text
     */
    public void sendSimpleMail(String to, String subject, String text);

    /**
     * 发送带附件的邮件 .
     * @param to 发送人
     * @param subject 邮件主题
     * @param content 内容文件
     * @param filePath 附件位置
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 使用html模板发送邮件 .
     * @param to 发送人
     * @param subject 邮件主题
     * @param content 邮件变量
     */
    public void sendHtmlMail(String to, String subject, String content);
}
