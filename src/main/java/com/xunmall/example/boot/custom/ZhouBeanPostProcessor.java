package com.xunmall.example.boot.custom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: wangyj03
 * @Date: 2021/9/28 15:43
 */
@Component
public class ZhouBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("初始化前");
        if (beanName.equals("orderStorage")){
            ((OrderStorage)bean).setGoodName("好贵");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后");
        if (beanName.equals("userService")){
            Object newProxyInstance = Proxy.newProxyInstance(ZhouBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("执行代理方法");
                    return method.invoke(bean,args);
                }
            });
            return newProxyInstance;
        }
        return bean;
    }
}
