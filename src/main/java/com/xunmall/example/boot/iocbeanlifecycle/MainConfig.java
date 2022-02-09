package com.xunmall.example.boot.iocbeanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyanjing
 * @date 2020/7/1 10:47
 */
@Configuration
@ComponentScan(basePackages = {"com.xunmall.example.boot.iocbeanlifecycle"})
public class MainConfig {

    @Bean(initMethod = "initPerson")
    public Person person() {
        Person person = new Person();
        person.setName("Eugene");
        person.setSex("Man");
        return person;
    }


}
