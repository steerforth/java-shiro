package com.steer.phoenix.redis.aop;


import com.steer.phoenix.redis.constants.JedisClusterStatus;
import com.steer.phoenix.redis.proxy.JedisProxy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author fangwk
 */
@Component
@Aspect
public class ReConnectAop {
    private Logger log = LoggerFactory.getLogger(ReConnectAop.class);

    private static final Object LOCK = new Object();

    @Autowired(required = false)
    private JedisProxy proxy;

    @Pointcut(value = "@annotation(com.steer.phoenix.redis.aop.ReConnect)")
    public void cutService() {
    }

    @Around(value = "cutService()")
    public Object around(JoinPoint point) throws Throwable {
        try {
            return ((ProceedingJoinPoint)point).proceed();
        } catch (Throwable t) {
            return handle(t,((ProceedingJoinPoint)point));
        }
    }

    private Object handle(Throwable t0, ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ReConnect reConnect = method.getAnnotation(ReConnect.class);
        Class<? extends Throwable>[] throwables = reConnect.value();
        int attempts = reConnect.maxAttempts();
        //
        return runWithRetry(attempts,proxy.getJedisProperty().getClusterNodeTimeout(),point,t0,throwables);
    }

    public Object runWithRetry(int attempts,int delay,ProceedingJoinPoint point,Throwable t0,Class<? extends Throwable>[] throwables) throws Throwable {
        if(attempts > 0 && check(t0,throwables)){
            log.debug("重试倒数次数:{}",attempts);
            try {
                proxy.setClusterStatus(JedisClusterStatus.failed);
                Thread.sleep(delay);
                synchronized (LOCK) {
                    log.debug("获取到锁");
                    if (JedisClusterStatus.failed.equals(proxy.getClusterStatus())) {
                        proxy.connect();
                    }
                    Object obj = point.proceed();
                    proxy.setClusterStatus(JedisClusterStatus.ok);
                    return obj;
                }
            } catch (Throwable t) {
               return runWithRetry(attempts - 1, delay, point, t, throwables);
            }
        }
        throw t0;
    }

    private boolean check(Throwable t0, Class<? extends Throwable>[] throwables) {
        for (Class<? extends Throwable> t:throwables) {
            if (t0.getClass() == t){
                return true;
            }
        }
        return false;
    }
}
