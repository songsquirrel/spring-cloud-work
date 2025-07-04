package com.song.basic_x.config.lock.annotation;

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
     * @return 锁超时释放时间，default 10
     */
    int leaseTime() default 30;

    /**
     * 时间单位
     * @return 时间单位，default second
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 获取锁, 是否等待
     * @return 是否等待，default true，如设置为false，则获取不到锁立即返回
     */
    boolean waiting() default true;

    /**
     * 获取锁等待时间
     * @return 获取锁等待时间，default 5, 如-1，则一直等待
     */
    int waitTime() default 5;
}
