package com.xpucsc.main.mapper;

import com.xpucsc.main.entity.Student;
import com.xpucsc.main.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
* @author Abraham
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2023-05-06 21:22:59
* @Entity com.xpucsc.main.entity.Teacher
*/
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

}




