package com.qiang.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liq
 * @date 2022/2/16 10:37
 */
@Data
public class CreateOrderDTO implements Serializable {
    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;
}
