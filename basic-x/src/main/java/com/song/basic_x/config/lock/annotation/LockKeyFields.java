package com.song.basic_x.config.lock.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockKeyFields {

    /**
     * 组成锁的key的属性
     * @return {"id", "name"}
     */
    String[] value() default {};
}
