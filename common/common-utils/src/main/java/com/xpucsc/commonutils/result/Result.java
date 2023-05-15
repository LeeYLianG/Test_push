package com.xpucsc.commonutils.result;

/**
 * @author Abraham
 * @date 2022/6/17 13:28
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

/**
 * 全局统一返回结果类
 *
 * 使用建造者模式
 *
 */
@ApiModel(value = "全局统一返回结果")
@Builder
@Getter
public class Result<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    @Tolerate
    public Result(){

    }

    public static<T> Result<T> ok(T data){
        return (Result<T>) Result.builder()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static<T> Result<T> fail(T data){
        return (Result<T>) builder()
                .code(ResultCode.FAIL.getCode())
                .message(ResultCode.FAIL.getMessage())
                .data(data)
                .build();
    }

}
