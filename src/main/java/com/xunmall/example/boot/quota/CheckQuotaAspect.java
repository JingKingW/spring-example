package com.xunmall.example.boot.quota;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Aspect
@Slf4j
@Component
public class CheckQuotaAspect {

    @Value("${checkQuota.lock.expirationTime:20000}")
    private long expirationTime;

    @Value("${checkQuota.lock.maxWaiteTime:20000}")
    private long maxWaiteTime;

    @Autowired
    @Lazy
    private QuotaService quotaService;

    @Around("@annotation(com.xunmall.example.boot.quota.CheckQuota)")
    public Object checkQuota(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null && method.isAnnotationPresent(CheckQuota.class)) {
            Boolean hasQuota = false;
            CheckQuota checkQuota = method.getAnnotation(CheckQuota.class);

            String lockKey = getLockKey(joinPoint, checkQuota.quotaType().getCode());
            Lock lock = this.getLock(lockKey, expirationTime);
            boolean isLock = this.tryLock(maxWaiteTime, lock);
            if (isLock) {
                long needQuota = 1;
                try {
                    if ((checkQuota.needQuota().contains("#"))) {
                        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
                        String[] parameters = discoverer.getParameterNames(method);
                        ExpressionParser parser = new SpelExpressionParser();
                        Expression expression = parser.parseExpression(checkQuota.needQuota());
                        EvaluationContext context = new StandardEvaluationContext();
                        int length = parameters.length;
                        if (length > 0) {
                            for (int i = 0; i < length; i++) {
                                context.setVariable(parameters[i], joinPoint.getArgs()[i]);
                            }
                        }
                        needQuota = expression.getValue(context, Long.class);
                    } else {
                        needQuota = Long.valueOf(checkQuota.needQuota());
                    }

                    hasQuota = quotaService.checkQuota(checkQuota.quotaType(), needQuota);
                    if (hasQuota) {
                        Object object = joinPoint.proceed();
                        quotaService.useQuota(checkQuota.quotaType(), needQuota);
                        return object;
                    } else {
                        throw new Exception("没有可用配额！");
                    }
                } finally {
                    unlock(lock);
                }
            } else {
                throw new Exception("获取锁资源失败！");
            }
        } else {
            return joinPoint.proceed();
        }
    }

    @Autowired
    private RedissonClient redissonClient;

    protected Lock getLock(String key, long expirationTime) {
        RLock lock = redissonClient.getFairLock(key);
        lock.expire(expirationTime, TimeUnit.MILLISECONDS);
        return lock;
    }

    protected boolean tryLock(long waitTime, Lock lock) throws InterruptedException {
        return ((RLock) lock).tryLock(waitTime, TimeUnit.MILLISECONDS);
    }

    protected void unlock(Lock lock) {
        try {
            lock.unlock();
        } catch (Exception e) {
            log.warn("unlock error : " + e.getMessage());
        }
    }

    private String getLockKey(ProceedingJoinPoint joinPoint, String quotaCode) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        StringBuilder sb = new StringBuilder();
        sb.append("lock-").append(targetName)
                .append(".").append(methodName)
                .append("#").append(quotaCode);

        return sb.toString();
    }
}