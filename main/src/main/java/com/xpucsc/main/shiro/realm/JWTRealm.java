package com.xpucsc.main.shiro.realm;

import com.xpucsc.commonutils.result.ResultCode;
import com.xpucsc.main.entity.Student;
import com.xpucsc.main.service.StudentService;
import com.xpucsc.main.shiro.authenticationToken.JWTToken;
import com.xpucsc.main.shiro.util.JWTUtil;
import com.xpucsc.main.shiro.util.aliveUser.AliveUser;
import com.xpucsc.main.threadlocal.ErrorThreadLocal;
import com.xpucsc.main.threadlocal.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @Author Abraham
 * @Date 2023/5/7 20:35
 * @Version 1.0
 */
@Component
@Slf4j
public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AliveUser aliveUser;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("正在准备授权...");
        String token = principalCollection.getPrimaryPrincipal().toString();
        String stuid = JWTUtil.getUserId(token);
        log.info("{} 正在授权...", stuid);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("user");
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken jwtToken) throws AuthenticationException {
        String token = jwtToken.getPrincipal().toString();
        String tokenId = JWTUtil.getTokenId(token);
        String stuId = JWTUtil.getUserId(token);
        Student student = aliveUser.check(stuId);

        /**
         * 未登录
         */
        if(student == null) {
            ErrorThreadLocal.set(ResultCode.NOT_LOGIN);
            throw new AuthenticationException("用户未登录");
        }
        String password = student.getPassword();

        // 验证token
        if(JWTUtil.verify(token, stuId, password)) {
            log.info("{} 成功认证...", stuId);
            UserThreadLocal.set(student);
        }else {
            if(JWTUtil.getExpiresAt(token).before(new Date())) {
                ErrorThreadLocal.set(ResultCode.TOKEN_EXPIRED);
            }else {
                ErrorThreadLocal.set(ResultCode.TOKEN_ERROR);
            }

            throw new AuthenticationException("token非法或者已经失效");
        }

        return new SimpleAuthenticationInfo(jwtToken.getPrincipal(), jwtToken.getCredentials(),
                ByteSource.Util.bytes("token"), this.getName());
    }
}
