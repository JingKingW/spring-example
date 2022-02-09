package com.xunmall.example.boot.lock;

/**
 * Created by Gimgoog on 2018/6/11.
 */
public class RedisLockService {

    private int number = 20;

    @LockAction(key = "#product")
    public void seckill(String product) {
        System.out.println(--number);
        if (number <= 0) {
            System.out.println(product + ":已经卖完了");
        } else {
            System.out.println(product + ":已经卖出 " + (20 - number));
        }
    }
}
