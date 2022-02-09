package com.xunmall.example.boot.proxy;

/**
 * Created by Gimgoog on 2018/2/11.
 */
public class MethodPerformance {
    private Long startTime;
    private Long endTime;
    private String methodName;

    public MethodPerformance(String methodName) {
        this.startTime = System.currentTimeMillis();
        this.methodName = methodName;
    }

    public void printMethodPerformance() {
        endTime = System.currentTimeMillis();
        Long executeTime = endTime - startTime;
        System.out.println(methodName + " :执行耗时 " + executeTime + "毫秒");
    }

}
