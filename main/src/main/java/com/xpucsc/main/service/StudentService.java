package com.xpucsc.main.service;

import com.xpucsc.main.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xpucsc.main.entity.StudentParty;
import com.xpucsc.main.entity.vo.LoginForm;
import com.xpucsc.main.enums.PoliticsEnum;

import java.util.List;
import java.util.Map;

/**
* @author Abraham
* @description 针对表【student】的数据库操作Service
* @createDate 2023-05-07 21:16:55
*/
public interface StudentService extends IService<Student> {

    Student login(String stuid);

    String checkLogin(LoginForm loginForm);


    List<Student> getStuList();

    Student getByStuId(Integer stuId);

    Student delByStuId(Integer stuId);

    List<Map<String, Object>> countPolitics(String politics);

    List<Student> checkPolitics(PoliticsEnum politics);

    Integer editStu(Student student);

}
