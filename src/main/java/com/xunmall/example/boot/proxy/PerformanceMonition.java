package com.xunmall.example.boot.proxy;

/**
 *
 * @author Gimgoog
 * @date 2018/2/11
 */
public class PerformanceMonition {

    private static ThreadLocal<MethodPerformance> methodPerformanceThreadLocal = new ThreadLocal<>();

    public static void startMonition(String methodName) {
        System.out.println("Begin Monition!");
        MethodPerformance methodPerformance = new MethodPerformance(methodName);
        methodPerformanceThreadLocal.set(methodPerformance);
    }

    public static void endMonition() {
        System.out.println("End Monition!");
        MethodPerformance methodPerformance = methodPerformanceThreadLocal.get();
        methodPerformance.printMethodPerformance();
    }


}
