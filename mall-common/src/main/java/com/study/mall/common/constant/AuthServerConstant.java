package com.study.mall.common.constant;

/**
 * @author Harlan
 * @date 2022 06 10 上午 11:30
 */
public class AuthServerConstant {

    private AuthServerConstant() {
        //DO NOTHING
    }

    public static final String SMS_CODE_CACHE_PREFIX = "sms:code:";

    public static final String LOGIN_USER = "loginUser";

    public static final String TEMP_USER_KEY = "user-key";

    public static final int TEMP_USER_COOKIE_TIME_OUT = 60 * 60 * 24 * 30;
}
