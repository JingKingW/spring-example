package com.xunmall.example.boot.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gimgoog on 2018/4/12.
 */
public class CompareBeforeOneJobTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-quartz.xml");
    }
}