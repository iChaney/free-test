package com.qiang.service.impl;

import cn.hutool.core.convert.Convert;
import com.qiang.feign.AccountFeignClient;
import com.qiang.feign.PaymentFeignClient;
import com.qiang.feign.StorageFeignClient;
import com.qiang.mapper.OrderPOMapper;
import com.qiang.model.dto.CreateOrderDTO;
import com.qiang.po.OrderPO;
import com.qiang.pojo.RestResponse;
import com.qiang.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liq
 * @date 2022/2/16 9:56
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    PaymentFeignClient paymentFeignClient;
    @Autowired
    AccountFeignClient accountFeignClient;
    @Autowired
    StorageFeignClient storageFeignClient;
    @Autowired
    OrderPOMapper orderPOMapper;

    @Override
    public RestResponse list() {
        return paymentFeignClient.list();
    }

    @Override
    public RestResponse timeout() {
        return paymentFeignClient.timeout();
    }

    /**
     * 创建订单
     * 1. insert订单表
     * 2. 用户的账户表money减少
     * 3. 库存表数量减少
     */
    @Override
    @GlobalTransactional
    public RestResponse create(CreateOrderDTO createOrderDto) {
        OrderPO orderPO = Convert.convert(OrderPO.class, createOrderDto);
        orderPOMapper.insertSelective(orderPO);
        accountFeignClient.reduce(createOrderDto.getUserId(), createOrderDto.getMoney());
        storageFeignClient.reduce(createOrderDto.getCommodityCode(), createOrderDto.getCount());
        return RestResponse.success();
    }
}
