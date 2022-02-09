package com.xunmall.example.boot.custom;

/**
 * @Author: wangyj03
 * @Date: 2021/9/28 10:50
 */
@Component("orderStorage")
public class OrderStorage {

    private String goodName;

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodsById(){
        return "IPhone 12 pro " +  goodName;
    }
}
