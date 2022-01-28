package com.qiang.exception;

/**
 * @author liq
 * @date 2022/1/18 16:52
 */
public class BizExeception extends RuntimeException {
    public BizExeception() {
    }

    public BizExeception(String message) {
        super(message);
    }
}
