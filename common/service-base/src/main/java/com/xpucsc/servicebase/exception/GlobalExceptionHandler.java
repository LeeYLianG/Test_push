package com.xpucsc.servicebase.exception;

import com.xpucsc.commonutils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abraham
 * @date 2022/6/19 14:07
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public Result<?> error(ParamException e){
        Map<String,String> errorParams = new HashMap<>();
        e.getErrors().forEach(fieldError -> {
            log.error("{} 参数错误",fieldError.getField());
            errorParams.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        e.printStackTrace();
        return Result
                .builder()
                .message("参数错误")
                .data(errorParams)
                .code(201)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result
                .builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Result<?> error(GlobalException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result
                .builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

}
