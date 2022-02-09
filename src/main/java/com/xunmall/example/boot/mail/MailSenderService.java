package com.xunmall.example.boot.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by Gimgoog on 2018/2/28.
 */
public class MailSenderService {
    @Value("${mailServer.host}")
    private String host;
    @Value("${mailServer.username}")
    private String username;
    @Value("${mailServer.password}")
    private String password;

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding("utf-8");
        return javaMailSender;
    }
}
