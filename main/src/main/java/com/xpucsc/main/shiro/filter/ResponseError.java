package com.xpucsc.main.shiro.filter;

import com.xpucsc.commonutils.result.ResultCode;
import com.xpucsc.main.threadlocal.ErrorThreadLocal;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Abraham
 * @date 2022/10/18 9:11
 */

public class ResponseError {

    @SneakyThrows
    public static void send(HttpServletRequest request, HttpServletResponse response) {
        ResultCode exception = ErrorThreadLocal.get();
        request.getRequestDispatcher("/xpucsc-api/unauthorized/exception/" + exception.getCode()
        + "/" + exception.getMessage()).forward(request,response);
    }
}
