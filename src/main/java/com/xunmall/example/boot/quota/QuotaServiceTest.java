package com.xunmall.example.boot.quota;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Created by Gimgoog on 2018/6/12.
 */
@Slf4j
public class QuotaServiceTest {

    @Test
    public void testRedisLock() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-redis.xml");

        QuotaService service = (QuotaService) applicationContext.getBean("quotaService");
        int threadCount = 22;
        CountDownLatch endCount = new CountDownLatch(threadCount);
        ExecutorService executorService = newFixedThreadPool(10);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        endCount.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    service.quotaKill("Iphone X");
                }

            });
            endCount.countDown();
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
