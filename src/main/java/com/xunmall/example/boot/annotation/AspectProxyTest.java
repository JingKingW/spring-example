package com.xunmall.example.boot.annotation;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gimgoog on 2018/2/12.
 */
public class AspectProxyTest {

    @Test
    public void testBefore() {
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory();
        Waiter waiter = new NativeWaiter();
        aspectJProxyFactory.setTarget(waiter);
        aspectJProxyFactory.addAspect(PreGreetingAspect.class);
        Waiter proxy = aspectJProxyFactory.getProxy();
        proxy.greetTo("tom");
        proxy.serveTo("jake");
    }

    @Test
    public void testEnhance() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        Waiter waiter = (Waiter) applicationContext.getBean("nativeWaiter");
        waiter.greetTo("jeremy");
        Seller seller = (Seller) waiter;
        seller.sell("jeremy");
    }


}
