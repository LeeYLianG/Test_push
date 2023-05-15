package com.xpucsc.main.service;

import com.xpucsc.main.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Abraham
* @description 针对表【teacher】的数据库操作Service
* @createDate 2023-05-06 21:22:59
*/
public interface TeacherService extends IService<Teacher> {

    Integer addTeacher(Teacher teacher);

    Integer editTeacher(Teacher teacher);
}
