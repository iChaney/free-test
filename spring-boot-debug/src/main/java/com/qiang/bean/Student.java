package com.qiang.bean;

import javax.annotation.Resource;

/**
 * @author liq
 * @date 2021/4/23 10:34
 */
//@Component
public class Student {
    @Resource
    Teacher teacher;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
