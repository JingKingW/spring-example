package com.xunmall.example.boot.iocbeanlifecycle;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author wangyanjing
 * @date 2020/7/1 10:47
 */
public class Person implements InitializingBean{
    private String name;
    private String sex;

    public Person(){
        System.out.println("执行构造方法。。。");
    }

    public void initPerson(){
        System.out.println("执行初始化方法。。。");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
