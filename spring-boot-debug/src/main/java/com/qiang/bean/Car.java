package com.qiang.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liq
 * @date 2021/12/30 16:15
 */
@Data
@Component
@ConfigurationProperties(prefix = "car")
public class Car {

    private String brand;

    private String color;

    private String masterName;

    private String type;
}
