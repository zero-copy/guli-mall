/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.study.mall.common.utils;

import org.apache.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R<T> extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R error(int code, String msg, Map<String, String> resultMap) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("data", resultMap);
        return  r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object data) {
        return ok().put("data", data);
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok(String msg, Object data) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("msg", msg);
        map.put("data", data);
        return ok(map);
    }

    public static R ok(PageUtils page) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("msg", "success");
        map.put("page", page);
        return ok(map);
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

    public boolean isOk() {
        return (Integer) this.get("code") == 0;
    }
}
