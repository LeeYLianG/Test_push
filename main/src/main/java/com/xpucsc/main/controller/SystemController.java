package com.xpucsc.main.controller;

import com.xpucsc.commonutils.result.Result;
import com.xpucsc.main.annotation.RateLimit;
import com.xpucsc.main.entity.Student;
import com.xpucsc.main.entity.vo.LoginForm;
import com.xpucsc.main.entity.vo.LoginVo;
import com.xpucsc.main.service.StudentService;
import com.xpucsc.main.service.TeacherService;
import com.xpucsc.main.shiro.util.aliveUser.AliveUser;
import com.xpucsc.main.threadlocal.UserThreadLocal;
import com.xpucsc.servicebase.exception.ParamException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Abraham
 * @Date 2023/5/6 21:33
 * @Version 1.0
 */
@Api(tags = "系统控制器")
@RestController
@Slf4j
@RequestMapping("/xpucsc-api/sys")
public class SystemController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AliveUser aliveUser;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 注册学生账号测试用
     * @param loginForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public Result<?> register(@Validated @ApiParam("登录表单") @RequestBody LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParamException(bindingResult.getFieldErrors());
        }
        Student student = new Student();
        student.setStuId(loginForm.getUserid());
        student.setPassword(new Md5Hash(loginForm.getPassword(), "abraham", 3).toString());
        student.setIdentity("10010011111111");
        studentService.save(student);

        return Result.ok(true);
    }


    @ApiOperation("登录接口")
    @RateLimit(cycle = 60, count = 5)
    @PostMapping("/student/login")
    public Result<?> login(@Validated @ApiParam("登录表单") @RequestBody LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ParamException(bindingResult.getFieldErrors());
        }
        log.info("{} 正在登录中...", loginForm.getUserid());
        String token = studentService.checkLogin(loginForm);

        Student student = UserThreadLocal.get();

        aliveUser.put(student);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setUserId(student.getStuId());

        log.info("{} 登录成功", loginForm.getUserid());
        return Result.ok(loginVo);
    }
}
