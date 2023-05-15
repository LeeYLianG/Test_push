package com.xpucsc.main.controller;

import com.xpucsc.commonutils.result.Result;
import com.xpucsc.main.entity.Student;
import com.xpucsc.main.entity.StudentParty;
import com.xpucsc.main.entity.Teacher;
import com.xpucsc.main.enums.PoliticsEnum;
import com.xpucsc.main.service.StudentPartyService;
import com.xpucsc.main.service.StudentService;
import com.xpucsc.main.service.TeacherService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Abraham
 * @Date 2023/5/8 16:58
 * @Version 1.0
 */
@Api(tags = "管理员(老师)控制器")
@RestController
@Slf4j
@RequestMapping("/xpucsc-api/authorized/admin")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentPartyService partyService;
    @Autowired
    private StudentService studentService;

    //老师查询全部学生信息
    @GetMapping("stuList")
    public Result<?> getStudentList(){
        List<Student> stuList = studentService.getStuList();
        return Result.ok(stuList);
    }
    //老师根据学号查询学生信息
    @GetMapping("/{stuId}")
    public Student getByStuId(@PathVariable Integer stuId){
        return studentService.getByStuId(stuId);
    }
    //创建老师信息
    @PostMapping
    public Result<?> addTeacher(@RequestBody Teacher teacher){
        Integer addTeacher = teacherService.addTeacher(teacher);
        return Result.ok(addTeacher);
    }
    //老师根据学号删除学生信息
    @DeleteMapping("/{stuId}")
    public Student delStuByStuId(@PathVariable Integer stuId){
        return studentService.delByStuId(stuId);
    }
    //查看政治面貌信息
    @GetMapping("/countPolitics")
    public Result<?> countPolitices(@RequestParam String politics){
        List<Map<String, Object>> maps = studentService.countPolitics(politics);
        return Result.ok(maps);
    }
    //老师查看不同政治面貌的学生信息
    @GetMapping("/checkParty")
    public Result<?> checkPolitics(@RequestParam PoliticsEnum politics){
        List<Student> students = studentService.checkPolitics(politics);
        return Result.ok(students);
    }
    //老师编辑自己的信息
    @PutMapping("/editTeacher")
    public Result<?> editTeacher(@RequestBody Teacher teacher){
        Integer edit = teacherService.editTeacher(teacher);
        return Result.ok(edit);
    }


    /*老师对党员的操作*/
    //老师查询对应政治面貌的学生
    @GetMapping("/getPartyStu")
    public Result<?> getPartyStu(@RequestParam PoliticsEnum party){
        List<StudentParty> partyStu = partyService.getPartyStu(party);
        return Result.ok(partyStu);
    }
    //添加党员
    @PostMapping("/addPartyStu")
    public Result<?> addPartyStu(@RequestBody StudentParty studentParty){
        boolean save = partyService.save(studentParty);
        return Result.ok(save);
    }
    //编辑党员信息
    @PutMapping("/editPartyStu")
    public Result<?> editPartyStu(@RequestBody StudentParty partyStu){
        Integer editPartyStu = partyService.editPartyStu(partyStu);
        return Result.ok(editPartyStu);
    }


}
