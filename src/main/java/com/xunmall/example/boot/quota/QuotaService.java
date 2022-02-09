package com.xunmall.example.boot.quota;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gimgoog on 2018/6/12.
 */
@Service
public class QuotaService {

    private static List<Map<String, Long>> quotaList;

    static {
        quotaList = new ArrayList<>();
        Map<String, Long> quota = new HashMap<>();
        quota.putIfAbsent("Iphone X", 20L);
        quotaList.add(quota);
    }

    public Boolean checkQuota(QuotaType quotaType, long needQuota) {
        long residue = findQuotaByCode(quotaType.getCode());
        if (residue >= needQuota) {
            return true;
        }
        return false;
    }

    private long findQuotaByCode(String code) {
        for (Map<String, Long> map : quotaList) {
            for (Map.Entry entry : map.entrySet()) {
                if (entry.getKey().equals(code)) {
                    return (long) entry.getValue();
                }
            }
        }
        return 0L;
    }

    public void useQuota(QuotaType quotaType, long needQuota) {
        String code = quotaType.getCode();
        for (Map<String, Long> map : quotaList) {
            for (Map.Entry entry : map.entrySet()) {
                if (entry.getKey().equals(code)) {
                    Long residue = (Long) entry.getValue() - needQuota;
                    map.put(code, residue > 0 ? residue : 0);
                }
            }
        }
    }

    @CheckQuota(quotaType = QuotaType.product)
    public void quotaKill(String product) {
        System.out.println(product + ":又卖出一台");
    }
}
