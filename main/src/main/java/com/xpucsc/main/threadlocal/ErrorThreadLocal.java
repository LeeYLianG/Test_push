package com.xpucsc.main.threadlocal;

import com.xpucsc.commonutils.result.ResultCode;
import org.springframework.stereotype.Component;


/**
 * @author Abraham
 * @date 2022/10/17 16:26
 */

@Component
public class ErrorThreadLocal {

    private static final ThreadLocal<ResultCode> threadLocal = new ThreadLocal<>();

    public static ResultCode get(){
        return threadLocal.get();
    }

    public static void set(ResultCode token){
        threadLocal.set(token);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
