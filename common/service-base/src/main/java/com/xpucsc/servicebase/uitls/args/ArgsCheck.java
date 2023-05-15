package com.xpucsc.servicebase.uitls.args;

import com.xpucsc.commonutils.result.ResultCode;
import com.xpucsc.servicebase.exception.GlobalException;
import org.springframework.util.StringUtils;

/**
 * @author Abraham
 * @date 2022/8/19 19:40
 */

public class ArgsCheck {

    public static void check(String... args){
        for (String arg : args) {
            if (StringUtils.isEmpty(arg)){
                throw new GlobalException(ResultCode.ARGUMENT_VALID_ERROR.getCode()
                        ,ResultCode.ARGUMENT_VALID_ERROR.getMessage());
            }
        }

    }
}
