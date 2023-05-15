package com.xpucsc.main.shiro.filter;

import com.xpucsc.commonutils.result.ResultCode;
import com.xpucsc.main.shiro.authenticationToken.JWTToken;
import com.xpucsc.main.threadlocal.ErrorThreadLocal;
import com.xpucsc.main.threadlocal.UserThreadLocal;
import com.xpucsc.servicebase.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Abraham
 * @Date 2023/5/7 22:14
 * @Version 1.0
 */
@Component
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     *  拦截器的前置  最先执行的
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * preHandle 执行完之后会执行这个方法
     * 在这个方法中 我们根据条件判断去去执行isLoginAttempt和executeLogin方法
     * 执行登录认证
     * @param request ServletRequest
     * @param response ServletResponse
     * @param mappedValue mappedValue
     * @return 是否成功
     */
    @Override
    //这个方法叫做  尝试进行登录的操作,如果token存在,那么进行提交登录,如果不存在说明可能是正在进行登录或者做其它的事情 直接放过即可
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            isLoginAttempt(request, response);
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            //todo：isLoginAttempt 抛出异常处理
            return false;
        }
    }


    /**
     * 这里我们去做一个判断请求头中的token信息是否为空 为空抛出异常 上边接受
     * 如果没有我们想要的请求头信息则直接返回false
     * */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            log.info("token 非法");
            ErrorThreadLocal.set(ResultCode.TOKEN_ERROR);
            throw new GlobalException();
        }
        return true;
    }

    /**
     * 执行登陆
     * 因为已经判断token不为空了,所以直接执行登陆逻辑
     * 讲token放入JwtToken类中去
     * 然后getSubject方法是调用到了MyRealm的 执行方法  因为上面我是抛错的所有最后做个异常捕获就好了
     * */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        JWTToken jwtToken = new JWTToken(token);
        // jwt提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e) {
            //异常处理
            ResponseError.send((HttpServletRequest)request,(HttpServletResponse) response);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        UserThreadLocal.remove();
        ErrorThreadLocal.remove();
    }
}
