package com.study.mall.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 10 下午 12:23
 */
@Data
public class UserRegisterVo implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 18, message = "用户名必须是6-18个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "密码必须是6-18个字符")
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号格式不正确")
    private String phoneNum;

    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "^[0-9]{4}$", message = "验证码为4位数字")
    private String code;
}
