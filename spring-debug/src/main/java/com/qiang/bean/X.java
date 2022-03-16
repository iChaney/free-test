package com.qiang.bean;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liq
 * @date 2021/9/28 14:49
 */
@Component
public class X {
    @Resource
    XService xService;
}
