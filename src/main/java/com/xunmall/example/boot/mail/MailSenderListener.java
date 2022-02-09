package com.xunmall.example.boot.mail;

import org.springframework.context.ApplicationListener;

/**
 * Created by wangyanjing on 2018/10/22.
 */
public class MailSenderListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent mailSendEvent) {
        MailSendEvent mse = mailSendEvent;
        System.out.println("MailSenderListener: 向" + mse.getTo() + "发送完一封邮件");
    }
}
