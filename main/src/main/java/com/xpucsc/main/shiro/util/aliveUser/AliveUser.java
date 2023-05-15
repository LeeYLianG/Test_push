package com.xpucsc.main.shiro.util.aliveUser;

import com.xpucsc.main.entity.Student;
import com.xpucsc.servicebase.uitls.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author Abraham
 * @Date 2023/5/7 17:43
 * @Version 1.0
 */
@Component
public class AliveUser {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private StringRedisTemplate redis;

    public void put(Student student) {
        redis.opsForValue().set("userid:" + student.getStuId(), jsonUtil.write(student), 7, TimeUnit.DAYS);
    }

    public Student check(String stuId) {
        String value = redis.opsForValue().get("userid:" + stuId);
        if(StringUtils.isEmpty(value)) {
            return null;
        }

        return (Student)jsonUtil.read(value, Student.class);
    }

    public boolean dead(String stuId) {
        return Boolean.TRUE.equals(redis.delete("userid:" + stuId));
    }


    // 删除redis所有的stuId 测试用
    public void flush() {
        Set<String> keys = redis.keys("userid:*");

        if(!CollectionUtils.isEmpty(keys)) {
            redis.delete(keys);
        }
    }

}
