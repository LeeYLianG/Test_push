package com.xpucsc.servicebase.uitls.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpucsc.servicebase.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Abraham
 * @date 2022/8/19 20:52
 */

@Component
public class JsonUtil {

    @Autowired
    private ObjectMapper jsonMapper;

    public String write(Object o){
        try {
            String value = jsonMapper.writeValueAsString(o);
            return value;
        } catch (JsonProcessingException e) {
            throw new GlobalException(201,"序列化错误");
        }
    }

    public Object read(String src,Class clazz){
        try {
//            JavaType javaType = jsonMapper.getTypeFactory().constructCollectionType(List.class,clazz);
            return jsonMapper.readValue(src,clazz);
        } catch (JsonProcessingException e) {
            throw new GlobalException(201,"反序列化错误");
        }
    }
}
