package com.xunmall.example.boot.mail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangyanjing on 2018/10/22.
 */
public class MailSenderTest {

    @Test
    public void testMailSenderEvent(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mailServer.xml");
        MailSender mailSender = (MailSender) applicationContext.getBean("mailSender");
        mailSender.sendMail("oewn5263@qq.com");
    }
}
