package com.xunmall.example.boot.quota;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface CheckQuota {
    //配额类型
    QuotaType quotaType();

    //配额需求量
    String needQuota() default "1";
}