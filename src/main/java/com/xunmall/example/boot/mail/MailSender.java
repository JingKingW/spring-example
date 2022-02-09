package com.xunmall.example.boot.mail;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by wangyanjing on 2018/10/22.
 */
public class MailSender implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void sendMail(String to){
        System.out.println("MailSender: 模拟发送邮件。。。");
        MailSendEvent mailSendEvent = new MailSendEvent(this.context,to);
        context.publishEvent(mailSendEvent);
    }
}
