package com.xpucsc.main.service;

import com.xpucsc.main.entity.StudentParty;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xpucsc.main.enums.PoliticsEnum;

import java.util.List;

/**
* @author Abraham
* @description 针对表【student_party】的数据库操作Service
* @createDate 2023-05-06 21:22:51
*/
public interface StudentPartyService extends IService<StudentParty> {

    List<StudentParty> getPartyStu(PoliticsEnum party);

    Integer editPartyStu(StudentParty partyStu);
}
