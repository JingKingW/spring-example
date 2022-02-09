package com.xunmall.example.boot.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Gimgoog on 2018/2/12.
 */
public class PerformanceHandler implements InvocationHandler {

    private Object target;

    public PerformanceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonition.startMonition(target.getClass().getName() + "." + method.getName());
        Object object = method.invoke(target, args);
        PerformanceMonition.endMonition();
        return object;
    }
}
