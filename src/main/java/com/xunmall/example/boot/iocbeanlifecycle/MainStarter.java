package com.xunmall.example.boot.iocbeanlifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangyanjing
 * @date 2020/7/1 10:47
 */
public class MainStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) annotationConfigApplicationContext.getBean("person");
        System.out.println(person.toString());
    }

}
