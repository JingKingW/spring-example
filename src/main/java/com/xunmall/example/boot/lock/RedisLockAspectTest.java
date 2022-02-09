package com.xunmall.example.boot.lock;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gimgoog on 2018/6/11.
 */
public class RedisLockAspectTest {

    @Test
    public void testRedisLock() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        RedisLockService service = (RedisLockService) applicationContext.getBean("redisLockService");
        int threadCount = 150;
        CountDownLatch endCount = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        endCount.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    service.seckill("Iphone X");
                }

            });
            endCount.countDown();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


