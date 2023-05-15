package com.xpucsc.main.filter;

import com.xpucsc.main.annotation.RateLimit;
import com.xpucsc.servicebase.exception.GlobalException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 防刷限流的拦截器
 * @author Abraham
 * @date 2022/11/24 18:10
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果请求的是方法，则需要做校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取目标方法上是否有指定注解
            RateLimit rateLimit = handlerMethod.getMethodAnnotation(RateLimit.class);
            if (rateLimit == null) {
                //说明目标方法上没有 RateLimit 注解
                return true;
            }
            //代码执行到此，说明目标方法上有 RateLimit 注解，所以需要校验这个请求是不是在刷接口
            // 获取请求IP地址
            String ip = getIpAddr(request);
            // 请求url路径
            String uri = request.getRequestURI();
            //存到redis中的key
            String key = "RateLimit:" + ip + ":" + uri;
            // 缓存中存在key，在限定访问周期内已经调用过当前接口
            if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
                // 访问次数自增1
                redisTemplate.opsForValue().increment(key, 1);
                // 超出访问次数限制
                if (redisTemplate.opsForValue().get(key) > rateLimit.count()) {
                    throw new GlobalException(501,rateLimit.msg());
                }
                // 未超出访问次数限制，不进行任何操作，返回true
            } else {
                // 第一次设置数据,过期时间为注解确定的访问周期
                redisTemplate.opsForValue().set(key, 1, rateLimit.cycle(), TimeUnit.SECONDS);
            }
            return true;
        }
        //如果请求的不是方法，直接放行
        return true;
    }

    //获取请求的归属IP地址
    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}
