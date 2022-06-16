package com.study.mall.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Harlan
 * @date 2022 06 12 下午 03:05
 */
@Data
public class SocialUserDto implements Serializable {

    private String accessToken;

    private String tokenType;

    private Long expiresIn;

    private String refreshToken;

    private String scope;

    private Long createdAt;

    private String id;

    private String name;

    private String avatarUrl;
}
