package com.qiang.service;

import com.qiang.model.dto.CreateOrderDTO;
import com.qiang.pojo.RestResponse;

/**
 * @author liq
 * @date 2022/2/16 9:56
 */
public interface OrderService {
    RestResponse list();

    RestResponse timeout();

    RestResponse create(CreateOrderDTO createOrderDto);
}
