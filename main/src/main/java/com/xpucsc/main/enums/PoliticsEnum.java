package com.xpucsc.main.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.models.auth.In;
import lombok.Getter;

@Getter
public enum PoliticsEnum {
    CPC(3, "中共党员"),
    PPM(2, "预备党员"),
    YLM(1, "共青团员"),
    MASS(0, "群众");

    //存到数据库的值
    @EnumValue
    private Integer politics;

    //前端显示的值，返回给前端的值
    @JsonValue
    private String title;

    PoliticsEnum(Integer politics, String title) {
        this.politics = politics;
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
