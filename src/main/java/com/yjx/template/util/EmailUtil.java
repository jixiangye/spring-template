package com.yjx.template.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by yejx on 2017/3/23.
 */
public class EmailUtil {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    public static void sendEmail(String toAddress, String context) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost("smtp.sina.com");

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        mailMessage.setTo(toAddress);
        mailMessage.setFrom("yjxdtc001@sina.com");
        mailMessage.setSubject("密码重置");
        mailMessage.setText(context);

        senderImpl.setUsername("yjxdtc001@sina.com"); // 根据自己的情况,设置username
        senderImpl.setPassword("111111111"); // 根据自己的情况, 设置password

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", " true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);

        logger.info("邮件发送成功");
    }
}
