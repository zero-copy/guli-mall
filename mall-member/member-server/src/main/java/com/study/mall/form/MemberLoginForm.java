package com.study.mall.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 12 下午 12:34
 */
@Data
public class MemberLoginForm implements Serializable {

    private String account;

    private String password;
}
