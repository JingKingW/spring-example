package com.xunmall.example.boot.custom;

/**
 * @Author: wangyj03
 * @Date: 2021/9/28 10:33
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
