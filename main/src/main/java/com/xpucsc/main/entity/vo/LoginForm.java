package com.xpucsc.main.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author Abraham
 * @Date 2023/5/6 21:42
 * @Version 1.0
 */
@ApiModel("登录表单")
@Data
public class LoginForm {

    @ApiModelProperty("学号")
    @NotBlank
    private String userid;

    @ApiModelProperty("密码")
    @NotBlank // 不为空注解
    private String password;
}
