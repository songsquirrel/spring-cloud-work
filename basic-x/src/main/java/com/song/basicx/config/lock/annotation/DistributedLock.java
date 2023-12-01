package com.song.basicx.config.lock.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    /**
     * 锁的key
     * @return key
     */
    String key();

    /**
     * 锁超时释放时间
     * @return 锁超时释放时间，default 8
     */
    int leaseTime() default 10;

    /**
     * 时间单位
     * @return 时间单位，default second
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 获取锁, 是否等待
     * @return 是否等待，default false
     */
    boolean waiting() default false;

    /**
     * 获取锁等待时间
     * @return 获取锁等待时间，default 2
     */
    int waitTime() default 2;
}
