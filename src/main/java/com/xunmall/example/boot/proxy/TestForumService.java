package com.xunmall.example.boot.proxy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

/**
 * Created by Gimgoog on 2018/2/11.
 */
public class TestForumService {

    @Test
    public void testNative() {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeTopic("100");
        forumService.removeForum("1023");
    }

    //jdk 动态代理实现 aop
    @Test
    public void testProxy() {
        ForumService target = new ForumServiceImpl();

        PerformanceHandler performanceHandler = new PerformanceHandler(target);

        ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), performanceHandler);

        proxy.removeTopic("10231");
        //proxy.removeForum("1000");
    }

    //cglib 动态代理实现 aop
    @Test
    public void testCglib(){
        CglibProxy cglibProxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl) cglibProxy.getPorxy(ForumServiceImpl.class);
        forumService.removeForum("10002");
        //forumService.removeTopic("123");
    }


    //引界增强横切面 aop
    @Test
    public void testAround(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        ForumService forumService = (ForumService) applicationContext.getBean("forumService");
        forumService.removeTopic("1000");

        Monitorable monitorable = (Monitorable) forumService;
        monitorable.setMonitorActive(true);
        forumService.removeTopic("1000");
    }



}
