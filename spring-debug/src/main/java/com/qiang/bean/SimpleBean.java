package com.qiang.bean;

import javax.annotation.PostConstruct;

/**
 * @author liq
 * @date 2021/8/4 15:12
 */
public class SimpleBean {
    private String name;

    @PostConstruct
    public void inityy() {
        System.out.println("ssssssssssssssssssssssimplebean");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
