package com.study.mall.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 17 下午 01:20
 */
@Data
public class TempUserInfo implements Serializable {

    private Long userId;

    private String userKey;

    private Integer integration;

    private boolean expired = true;
}
