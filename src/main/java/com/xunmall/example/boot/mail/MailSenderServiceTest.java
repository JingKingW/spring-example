package com.xunmall.example.boot.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;

/**
 * Created by Gimgoog on 2018/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mailServer.xml"})
public class MailSenderServiceTest {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送简单文本邮件
     *
     * @throws Exception
     */
    @Test
    public void testSimpleMailSender() throws Exception {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("wangyj@ichile.com.cn");
        simpleMailMessage.setTo("owen5263@qq.com");
        simpleMailMessage.setSubject("mailServer test");
        simpleMailMessage.setText("这是一份关怀的邮件");
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送存在附件的邮件
     * @throws Exception
     */
    @Test
    public void testAttachmentMailSender() throws Exception {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("wangyj@ichile.com.cn");
        mimeMessageHelper.setTo("owen5263@qq.com");
        mimeMessageHelper.setSubject("mailServer attachment");
        mimeMessageHelper.setText("存在附件的邮件");
        ClassPathResource classPathResource = new ClassPathResource("time.jpg");
        mimeMessageHelper.addAttachment("time.jpg", classPathResource);
        javaMailSender.send(mimeMailMessage);
    }

    /**
     * 发送富文本邮件
     * @throws Exception
     */
    @Test
    public void testHTMLMailSender() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("wangyj@ichile.com.cn");
        mimeMessageHelper.setTo("owen5263@qq.com");
        mimeMessageHelper.setSubject("富文本邮件");
        mimeMessageHelper.setText("<html><body><img src='cid:logo'>" +
                "<h4>富文本邮件</h4>"+
                "</body></html>",true);
        FileSystemResource fileSystemResource = new FileSystemResource("D:\\Pictures\\240450-140HZP45790.jpg");
        mimeMessageHelper.addInline("logo",fileSystemResource);
        javaMailSender.send(mimeMessage);
    }

}