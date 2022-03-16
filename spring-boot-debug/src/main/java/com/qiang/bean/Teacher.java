package com.qiang.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @author liq
 * @date 2021/7/13 17:51
 */
@Component
@ConditionalOnBean(name = "student")
public class Teacher {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
