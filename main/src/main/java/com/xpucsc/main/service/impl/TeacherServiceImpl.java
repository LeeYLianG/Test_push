package com.xpucsc.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpucsc.main.entity.Teacher;
import com.xpucsc.main.service.TeacherService;
import com.xpucsc.main.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Abraham
* @description 针对表【teacher】的数据库操作Service实现
* @createDate 2023-05-06 21:22:59
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{


    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Integer addTeacher(Teacher teacher) {
        Integer insertTeacher = teacherMapper.insert(teacher);
        return insertTeacher;
    }

    @Override
    public Integer editTeacher(Teacher teacher) {
        LambdaQueryWrapper<Teacher> teacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        teacherLambdaQueryWrapper.eq(Teacher::getTeacherId,teacher.getTeacherId());
        Integer update = teacherMapper.update(teacher, teacherLambdaQueryWrapper);
        return update;
    }
}




