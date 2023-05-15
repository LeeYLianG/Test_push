package com.xpucsc.main.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MALE(0,"男"),
    FEMALE(1,"女");

    @EnumValue
    private Integer id;

    @JsonValue
    private String sex;

    SexEnum(Integer id, String sex) {
        this.id = id;
        this.sex = sex;
    }

//    @JsonCreator
//    public static SexEnum forValue(Integer value){
//        SexEnum[] values = SexEnum.values();
//        return Stream.of(values).filter(it -> it.getId().equals(value)).findAny().get();
//    }
}
