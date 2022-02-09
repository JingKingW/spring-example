package com.xunmall.example.boot.custom;

import java.lang.reflect.InvocationTargetException;

/**
 * @author wangyj03
 * @date 2021/9/27
 */
public class MainApp {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        ZhouApplicationContext zhouApplicationContext = new ZhouApplicationContext(AppConfig.class);

        UserService userService = (UserService) zhouApplicationContext.getBean("userService");

        String userName = userService.printlnUserName(); // 1、代理逻辑 2、实际逻辑

        System.out.println(userName);
    }

}
