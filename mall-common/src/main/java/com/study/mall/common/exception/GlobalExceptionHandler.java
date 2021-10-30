package com.study.mall.common.exception;

import com.study.mall.common.enums.ErrorCodeEnum;
import com.study.mall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异处理
 * @author Harlan
 * @date 2021 10 17 0:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验异常: {} --- {}", e.getClass(), e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>(1);
        bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return R.error(ErrorCodeEnum.VALID_EXCEPTION.getCode(), ErrorCodeEnum.VALID_EXCEPTION.getMessage(), errorMap);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MallException.class)
    public R handleRRException(MallException e){
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }


    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error("异常: {} --- {} --- {}", e.getClass(), e.getMessage(), e.getStackTrace());
        e.printStackTrace();
        return R.error(ErrorCodeEnum.UNKNOWN_EXCEPTION.getCode(), ErrorCodeEnum.UNKNOWN_EXCEPTION.getMessage());
    }
}
