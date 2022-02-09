package com.xunmall.example.boot.proxy;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by Gimgoog on 2018/2/12.
 */
public class ControllabelPerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable {

    private ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    @Override
    public void setMonitorActive(boolean active) {
        threadLocal.set(active);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object object = null;
        if (threadLocal.get() != null && threadLocal.get()) {
            PerformanceMonition.startMonition(mi.getClass().getName() + "." + mi.getMethod().getName());
            object = super.invoke(mi);
            PerformanceMonition.endMonition();
        } else {
            object = super.invoke(mi);
        }
        return object;
    }
}
