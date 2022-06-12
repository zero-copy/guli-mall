package com.study.mall.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 12 下午 12:11
 */
@Data
public class UserLoginForm implements Serializable {

    private String account;

    private String password;
}
