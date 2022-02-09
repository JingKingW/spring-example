package com.xunmall.example.boot.custom;

/**
 * Created by wangyanjing on 2021/9/28.
 */
public class BeanDefinition {
    private String beanName;
    private Class clazz;
    private String type;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "beanName='" + beanName + '\'' +
                ", clazz=" + clazz +
                ", type='" + type + '\'' +
                '}';
    }
}
