package com.xpucsc.main.mapper;

import com.xpucsc.main.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpucsc.main.service.StudentService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* @author Abraham
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-05-07 21:16:55
* @Entity com.xpucsc.main.entity.Student
*/
public interface StudentMapper extends BaseMapper<Student> {

    @Select("SELECT * FROM `student`" +
            "WHERE `stu_id` = #{stuid}")
    Student login(@Param("stuid") String stuid);


    //已用条件构造器替代
   /* @Select("SELECT * FROM `student` WHERE `stu_id`= #{stuid}")
    Student selectByStuId(Integer stuid);*/


}




