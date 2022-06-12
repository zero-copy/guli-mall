package com.study.mall.common.enums;

/**
 * @author Harlan
 * @date 2021 10 17 1:28
 */
public enum ErrorCodeEnum {

    /**
     * 系统异常
     */
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),

    /**
     * 参数校验异常
     */
    VALID_EXCEPTION(10001, "参数校验失败"),

    /**
     * 验证码异常
     */
    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高"),

    /**
     * 用户登陆错误
     */
    LOGIN_ACCOUNT_PASSWORD_INVAILD_EXCEPTION(10003, "验证码获取频率太高"),

    /**
     * 商品上架异常
     */
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常");

    private final int code;

    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
