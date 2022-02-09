package com.xunmall.example.boot.annotation;

/**
 * Created by Gimgoog on 2018/2/11.
 */
public class FormService {

    @NeedTest(requried = false)
    public String getOneById(String name) {
        return name + ":你是很重要的人!";
    }

    @NeedTest
    public String getListByCondition(String param) {
        return "查询参数是：" + param;
    }
}
