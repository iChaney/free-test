package com.qiang.config;

import com.qiang.exception.BizExeception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liq
 * @date 2022/1/18 10:19
 */
@RestControllerAdvice
public class GlobalExepectionHandler {

    @ExceptionHandler({IllegalArgumentException.class, BizExeception.class})
    public String handleRuntimeException(Exception e) {
        return e.getMessage();
    }
}
