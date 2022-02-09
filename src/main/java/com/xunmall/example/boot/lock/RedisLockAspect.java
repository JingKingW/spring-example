package com.xunmall.example.boot.lock;

import com.xunmall.example.redis.lock.DistributedLock;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * Created by Gimgoog on 2018/6/11.
 */
@Aspect
public class RedisLockAspect {

    DistributedLock distributedLock = new DistributedLock();

    @Pointcut("execution(* com.xunmall..*.*(..)) && @annotation(com.xunmall.example.boot.lock.LockAction)")
    public void redisLockJoinPoint() {
    }

    @Around("redisLockJoinPoint()")
    public Object redidLockAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String targetName = proceedingJoinPoint.getClass().getName();
        String methodName = method.getName();
        Object[] params = proceedingJoinPoint.getArgs();

        if (method != null && method.isAnnotationPresent(LockAction.class)) {
            LockAction lockAction = method.getAnnotation(LockAction.class);

            String keyStr = getLockKey(method, targetName, methodName, lockAction.key(), params);
            //System.out.println(keyStr);
            String identifier = distributedLock.lockWithTimeout(keyStr,lockAction.expirationTime(), lockAction.maxWaitTime());
            try {
                proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                distributedLock.releaseLock(keyStr, identifier);
            }
        }
        return null;
    }

    private String getLockKey(Method method, String targetName, String methodName, String key, Object[] arguments) {

        StringBuilder sb = new StringBuilder();
        sb.append("lock-").append(targetName).append(".").append(methodName);

        if (StringUtils.isNotEmpty(key)) {
            String keyStr = key;
            if (!StringUtils.isBlank(keyStr)) {
                LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
                String[] parameters = discoverer.getParameterNames(method);
                ExpressionParser parser = new SpelExpressionParser();
                Expression expression = parser.parseExpression(keyStr);
                EvaluationContext context = new StandardEvaluationContext();
                int length = parameters.length;
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        context.setVariable(parameters[i], arguments[i]);
                    }
                }
                String keysValue = expression.getValue(context, String.class);
                sb.append("#").append(keysValue);
            }
        }
        return sb.toString();
    }


}
