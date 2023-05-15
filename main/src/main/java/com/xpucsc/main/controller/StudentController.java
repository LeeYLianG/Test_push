package com.xpucsc.main.controller;

import com.xpucsc.commonutils.result.Result;
import com.xpucsc.main.entity.Student;
import com.xpucsc.main.mapper.StudentMapper;
import com.xpucsc.main.service.StudentService;
import com.xpucsc.main.shiro.util.aliveUser.AliveUser;
import com.xpucsc.main.threadlocal.UserThreadLocal;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Abraham
 * @Date 2023/5/6 21:31
 * @Version 1.0
 */
@Api(tags = "学生账号控制器")
@RestController
@Slf4j
@RequestMapping("/xpucsc-api/authorized/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AliveUser aliveUser;

    @GetMapping("/info")
    public Result<?> getInfo() {
        Student student = UserThreadLocal.get();
        return Result.ok(student);
    }

    @PutMapping("stuEdit")
    public Result<?> stuEdit(@RequestBody Student student){
        Integer stuEdit = studentService.editStu(student);
        return Result.ok(stuEdit);
    }


}
