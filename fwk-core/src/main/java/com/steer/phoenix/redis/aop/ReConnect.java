package com.steer.phoenix.redis.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReConnect {
    Class<? extends Throwable>[] value() default {};
    int maxAttempts() default 3;
    int delay() default 1000;
}
