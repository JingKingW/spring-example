package com.xunmall.example.boot.annotation;

/**
 * Created by Gimgoog on 2018/2/13.
 */
public class SmartSeller implements Seller {
    @Override
    public void sell(String name) {
        System.out.println("sell a something to " + name);
    }
}
