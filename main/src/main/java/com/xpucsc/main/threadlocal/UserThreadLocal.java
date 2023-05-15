package com.xpucsc.main.threadlocal;

import com.xpucsc.main.entity.Student;
import org.springframework.stereotype.Component;

/**
 * @author Abraham
 * @date 2022/10/17 16:26
 */

@Component
public class UserThreadLocal {

    private static ThreadLocal<Student> threadLocal = new ThreadLocal<>();

    public static Student get(){
        return threadLocal.get();
    }

    public static void set(Student token){
        threadLocal.set(token);
    }

    public static void remove() {
        threadLocal.remove();
    }


}
