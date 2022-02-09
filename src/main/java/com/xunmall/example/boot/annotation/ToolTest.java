package com.xunmall.example.boot.annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by Gimgoog on 2018/2/11.
 */
public class ToolTest {

    @Test
    public void testAnnotaion() {
        try {
            Class clazz = Class.forName("com.xunmall.example.boot.annotation.FormService");
            Method[] methods = clazz.getDeclaredMethods();
            System.out.println(methods.length);
            for (Method method : methods) {
                NeedTest needTest = method.getAnnotation(NeedTest.class);
                if (needTest != null) {
                    if (needTest.requried() == true) {
                        System.out.println(method.getName() + " 必选标注");
                    } else {
                        System.out.println(method.getName() + " 非必须标注");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
