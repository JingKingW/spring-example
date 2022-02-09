package com.xunmall.example.boot.quota;

import java.util.Arrays;

public enum QuotaType {
    product("Iphone X", "限制商品");

    private String code;
    private String name;

    private QuotaType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static QuotaType getByCode(String code) {
        QuotaType quotaType = Arrays.stream(QuotaType.values())
                .filter(aQuotaType -> aQuotaType.code.equals(code))
                .findFirst().get();
        return quotaType;
    }
}
