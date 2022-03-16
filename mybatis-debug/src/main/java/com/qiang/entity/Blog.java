package com.qiang.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liq
 * @date 2021/5/20 18:04
 */
@Data
public class Blog implements Serializable {
    private static final long serialVersionUID = -3783634924381505141L;

    private int id;
    private String name;
}
