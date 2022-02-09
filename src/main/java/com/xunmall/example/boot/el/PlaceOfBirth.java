package com.xunmall.example.boot.el;

/**
 * Created by wangyanjing on 2018/11/2.
 */
public class PlaceOfBirth {
    private String country;
    private String province;

    public PlaceOfBirth(String country,String province){
        this.country = country;
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
