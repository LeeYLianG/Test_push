package com.xpucsc.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpucsc.main.entity.Student;
import com.xpucsc.main.entity.StudentParty;
import com.xpucsc.main.entity.vo.LoginForm;
import com.xpucsc.main.enums.PoliticsEnum;
import com.xpucsc.main.service.StudentService;
import com.xpucsc.main.mapper.StudentMapper;
import com.xpucsc.main.shiro.util.JWTUtil;
import com.xpucsc.servicebase.exception.GlobalException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.LanguageCallback;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Abraham
 * @description 针对表【student】的数据库操作Service实现
 * @createDate 2023-05-07 21:16:55
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

/*    @Autowired
    private StudentMapper studentMapper;*/

    @Override
    public Student login(String stuid) {
        return baseMapper.login(stuid);
    }

    @Override
    public String checkLogin(LoginForm loginForm) {
        Subject subject = SecurityUtils.getSubject();
        String stuid = loginForm.getUserid();
        String password = loginForm.getPassword();
        try {
            UsernamePasswordToken passwordToken = new UsernamePasswordToken(stuid, password);
            subject.login(passwordToken);
            String token = JWTUtil.sign(stuid, "user", new Md5Hash(password, "abraham", 3).toString());
            return token;
        } catch (Exception e) {
            throw new GlobalException(207, "用户名或密码错误");
        }
    }

    //老师查询所有学生信息
    @Override
    public List<Student> getStuList() {
        return baseMapper.selectList(null);
    }

    //老师根据学号查询学生信息
    @Override
    public Student getByStuId(Integer stuId) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getStuId, stuId);
        return baseMapper.selectOne(studentLambdaQueryWrapper);
    }
    //老师根据StuId删除学生信息
    @Override
    public Student delByStuId(Integer stuId) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getStuId, stuId);
        return baseMapper.selectOne(studentLambdaQueryWrapper);
    }
    //老师查询学生政治面貌总数
    @Override
    public List<Map<String, Object>> countPolitics(String politics) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("politics");
        queryWrapper.select("politics,count(*) as '总数'");
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        return maps;
    }
    //老师查询不同政治面貌的学生信息
    @Override
    public List<Student> checkPolitics(PoliticsEnum politics) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据政治面貌的条件，查询对应的列表
        studentLambdaQueryWrapper.func(studentLambdaQueryWrapper1 ->
                studentLambdaQueryWrapper1.eq(Student::getPolitics, politics.getPolitics()));
        List<Student> getPolitics = baseMapper.selectList(studentLambdaQueryWrapper);
        return getPolitics;
    }
    //老师根据sutId编辑学生信息
    @Override
    public Integer editStu(Student student) {
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getStuId,student.getStuId());
        Integer update = baseMapper.update(student, studentLambdaQueryWrapper);
        return update;
    }




}