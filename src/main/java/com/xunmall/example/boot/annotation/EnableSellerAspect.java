package com.xunmall.example.boot.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 使用引界增强类型
 * Created by Gimgoog on 2018/2/13.
 */
@Aspect
public class EnableSellerAspect {
    @DeclareParents(value = "com.xunmall.example.boot.annotation.NativeWaiter", defaultImpl = SmartSeller.class)
    public Seller seller;

    @AfterReturning("@annotation(com.xunmall.example.boot.annotation.NeedTest)")
    public void needTestFun() {
        System.out.println("needTestFun executed!");
    }

    @Around("execution(* greetTo(..)) && target(com.xunmall.example.boot.annotation.NativeWaiter)")
    public void joinPointAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("joinPointAccess execute!");
        System.out.println(joinPoint.getArgs()[0]);
        System.out.println(joinPoint.getTarget().getClass());
        joinPoint.proceed();
    }

}
