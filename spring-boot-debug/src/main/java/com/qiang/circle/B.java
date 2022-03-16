package com.qiang.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liq
 * @date 2021/7/22 18:13
 */
@Component
public class B {
    @Autowired
    private A a;
}
