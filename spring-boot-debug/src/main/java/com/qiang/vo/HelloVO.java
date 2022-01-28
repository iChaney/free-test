package com.qiang.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liq
 * @date 2022/1/6 16:34
 */
@Data
public class HelloVO implements Serializable {
    private String name;
    private Integer age;
    private String clothColor;
}
