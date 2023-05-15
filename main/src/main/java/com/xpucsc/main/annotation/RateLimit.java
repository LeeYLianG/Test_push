package com.xpucsc.main.annotation;

import java.lang.annotation.*;

/**
 * @author Abraham
 * @data 2022/11/24 18:04
 */

/**
 * 用于防刷限流的注解
 *      默认是5秒内只能调用一次
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /** 限流的key */
    String key() default "xpucsc:limit:";

    /** 周期,单位是秒 */
    int cycle() default 5;

    /** 请求次数 */
    int count() default 1;

    /** 默认提示信息 */
    String msg() default "api调用过快";
}

