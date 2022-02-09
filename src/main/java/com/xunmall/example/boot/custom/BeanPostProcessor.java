package com.xunmall.example.boot.custom;

/**
 * @Author: wangyj03
 * @Date: 2021/9/28 10:35
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);
}
