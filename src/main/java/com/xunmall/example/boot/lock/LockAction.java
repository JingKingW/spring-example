package com.xunmall.example.boot.lock;

import java.lang.annotation.*;

/**
 * Created by Gimgoog on 2018/6/11.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface LockAction {

    String key() default "";

    long expirationTime() default 5000L;

    long maxWaitTime() default 1000L;
}
