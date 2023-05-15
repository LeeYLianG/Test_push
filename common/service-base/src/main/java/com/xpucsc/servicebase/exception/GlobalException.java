package com.xpucsc.servicebase.exception;

import com.xpucsc.commonutils.result.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abraham
 * @date 2022/6/19 14:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException{

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("错误信息")
    private String message;

    public GlobalException(ResultCode error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
