package me.cc200.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
@Aspect
public class CustomLogAop {

    @Pointcut("@annotation(me.cc200.base.aop.CustomLog)")
    public void pointCutCustomLog() {}

    @Around("pointCutCustomLog()")
    public Object around(ProceedingJoinPoint p) throws Throwable {
        MethodSignature sign = (MethodSignature) p.getSignature();
        Method method = sign.getMethod();

        CustomLog customLog = method.getAnnotation(CustomLog.class);
        log.info("custom log ==> name: {}, type: {}, desc: {}",
                customLog.name(), customLog.type(), customLog.desc());

        log.info("custom log ==> method: {}.{}()", method.getDeclaringClass().getName(), method.getName());

        Object[] args = p.getArgs();
        if (customLog.isLogParams()) {
            log.info("custom log ==> log params: {}", args);
        }

        long startMs = System.currentTimeMillis();
        try {
            Object result = p.proceed();
            if (customLog.isLogResult()) {
                log.info("custom log ==> log result: {}", result);
            }
            return result;
        } catch (Throwable throwable) {
            log.warn("custom log ==> exception: {}", throwable.getMessage());
            throwable.printStackTrace();
            throw throwable;
        } finally {
            long costMs = System.currentTimeMillis() - startMs;
            log.info("custom log ==> method cost {} ms", costMs);
        }
    }
}
