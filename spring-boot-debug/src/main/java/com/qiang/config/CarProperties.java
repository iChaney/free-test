package com.qiang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liq
 * @date 2022/1/21 17:01
 */
@Data
@ConfigurationProperties(prefix = "custom.car")
public class CarProperties {
    private String brand;
    private String type;
    private String color;
}
