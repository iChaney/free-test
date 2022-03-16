package com.qiang.bean;

import org.springframework.core.convert.support.DefaultConversionService;

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
        this.name = "小张";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        DefaultConversionService conversionService = new DefaultConversionService();
        Teacher teacher = conversionService.convert(new SimpleBean(), Teacher.class);
    }
}
