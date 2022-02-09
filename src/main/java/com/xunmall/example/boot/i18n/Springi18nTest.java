package com.xunmall.example.boot.i18n;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by wangyanjing on 2018/10/19.
 */
public class Springi18nTest {

    @Test
    public void testCustomi18n() {
        String[] configs = {"spring-i18n.xml"};
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configs);
        MessageSource ms = (MessageSource) applicationContext.getBean("mySource");
        Object[] params = {"John", new GregorianCalendar().getTime()};
        String str1 = ms.getMessage("greeting.common", params, Locale.US);
        String str2 = ms.getMessage("greeting.common", params, Locale.CHINA);
        System.out.println(str1);
        System.out.println(str2);
    }

    @Test
    public void testContaineri18n() {
        String[] configs = {"spring-i18n.xml"};
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configs);
        Object[] params = {"John", new GregorianCalendar().getTime()};
        String str1 = applicationContext.getMessage("greeting.common", params, Locale.US);
        String str2 = applicationContext.getMessage("greeting.common", params, Locale.CHINA);
        System.out.println(str1);
        System.out.println(str2);
        String str3 = applicationContext.getMessage("greeting.morning", params, Locale.US);
        String str4 = applicationContext.getMessage("greeting.morning", params, Locale.CHINA);
        System.out.println(str3);
        System.out.println(str4);
    }

}
