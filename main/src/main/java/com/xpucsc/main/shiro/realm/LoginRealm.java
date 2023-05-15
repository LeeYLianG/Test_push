package com.xpucsc.main.shiro.realm;

import com.xpucsc.main.entity.Student;
import com.xpucsc.main.service.StudentService;
import com.xpucsc.main.shiro.util.aliveUser.AliveUser;
import com.xpucsc.main.threadlocal.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author Abraham
 * @Date 2023/5/7 17:37
 * @Version 1.0
 */

@Component
@Slf4j
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AliveUser aliveUser;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }


    /**
     * 只有当检测用户需要权限或者需要判定角色的时候才会走
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证, 如果没有权限注解的话就不会去走上面的方法只会走这个方法
     * 其实就是 过滤器传过来的token 然后进行 验证 authenticationToken.toString() 获取的就是
     * 你的token字符串,然后你在里面做逻辑验证就好了,没通过的话直接抛出异常就可以了
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String stuId = authenticationToken.getPrincipal().toString();
        Student student = aliveUser.check(stuId);

        if(student == null) {
            student = studentService.login(stuId);
        }

        if(StringUtils.isEmpty(student.getPassword())) {
            throw new AuthenticationException();
        }

        AuthenticationInfo info = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                student.getPassword(),
                ByteSource.Util.bytes("abraham"), stuId);

        UserThreadLocal.set(student);
        return info;
    }
}
