/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.study.mall.common.lang;

import com.study.mall.common.utils.PageUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private T data;

    private PageUtils page;

    public static<T> R<T> getInstance(Integer code, String msg, T data, PageUtils page) {
        return new R<>(code, msg, data, page);
    }

    public static<T> R<T> error(Integer code, String msg, T data) {
        return getInstance(code, msg, data, null);
    }

    public static<T> R<T> error(Integer code, String msg) {
        return error(code, msg, null);
    }

    public static<T> R<T> error(String msg, T data) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg, data);
    }

    public static<T> R<T> error(String msg) {
        return error(msg, null);
    }

    public static<T> R<T> error(Integer code, T data) {
        return error(code, "未知异常，请联系管理员", data);
    }

    public static<T> R<T> error() {
        return error("未知异常，请联系管理员");
    }

    public static<T> R<T> ok(Integer code, String msg, T data, PageUtils page) {
        return getInstance(code, msg, data, page);
    }

    public static<T> R<T> ok(String msg, T data) {
        return ok(0, msg, data, null);
    }

    public static<T> R<T> ok(String msg, PageUtils page) {
        return ok(0, msg, null, page);
    }

    public static<T> R<T> ok(T data) {
        return ok("success", data);
    }

    public static<T> R<T> ok(PageUtils page) {
        return ok("success", page);
    }

    public static<T> R<T> ok() {
        return ok(null);
    }

    public static<T> R<T> put(String msg, T data) {
        return R.ok(data);
    }

    public static<T> R<T> put(String msg, PageUtils page) {
        return R.ok(page);
    }
}
