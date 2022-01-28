package com.qiang.circle;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: liq
 * @date: 2021/4/18 22:51
 */
@Component
public class B {
    @Resource
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
