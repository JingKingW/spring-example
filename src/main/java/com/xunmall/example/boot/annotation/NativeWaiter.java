package com.xunmall.example.boot.annotation;

/**
 * Created by Gimgoog on 2018/2/12.
 */
public class NativeWaiter implements Waiter {
    @Override
    @NeedTest
    public void greetTo(String clientName) {
        System.out.println("Welcome to kfc!" + clientName);
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("What Can I do for you!" + clientName);
    }
}
