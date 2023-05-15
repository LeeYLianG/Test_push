package com.xpucsc.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpucsc.main.entity.StudentParty;
import com.xpucsc.main.enums.PoliticsEnum;
import com.xpucsc.main.service.StudentPartyService;
import com.xpucsc.main.mapper.StudentPartyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Abraham
* @description 针对表【student_party】的数据库操作Service实现
* @createDate 2023-05-06 21:22:51
*/
@Service
public class StudentPartyServiceImpl extends ServiceImpl<StudentPartyMapper, StudentParty>
    implements StudentPartyService{

    @Override
    public List<StudentParty> getPartyStu(PoliticsEnum party) {
        LambdaQueryWrapper<StudentParty> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(StudentParty::getParty,party.getPolitics());
        List<StudentParty> students = baseMapper.selectList(studentLambdaQueryWrapper);
        return students;
    }

    @Override
    public Integer editPartyStu(StudentParty partyStu) {
        LambdaQueryWrapper<StudentParty> partyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        partyLambdaQueryWrapper.eq(StudentParty::getStuId,partyStu.getStuId());
        Integer editPartyStu = baseMapper.update(partyStu, partyLambdaQueryWrapper);
        return editPartyStu;
    }

}




