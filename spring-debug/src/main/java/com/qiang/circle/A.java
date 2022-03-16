package com.qiang.circle;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: liq
 * @date: 2021/4/18 22:50
 */
@Component
public class A {
    @Resource
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
