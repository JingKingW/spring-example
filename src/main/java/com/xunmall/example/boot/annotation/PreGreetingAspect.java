package com.xunmall.example.boot.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by Gimgoog on 2018/2/12.
 */
@Aspect
public class PreGreetingAspect {

    @Before("execution(* greetTo(..))")
    public void beforeGreeting(){
        System.out.println("How are you！");
    }
}
