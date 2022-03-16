package com.qiang.controller;

import com.qiang.model.dto.CreateOrderDTO;
import com.qiang.pojo.RestResponse;
import com.qiang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author liq
 * @date 2022/2/11 14:33
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public RestResponse list() {
//        return restTemplate.getForObject("http://192.168.1.94:8081/course/list", RestResponse.class);
        return orderService.list();
    }

    /**
     * 连接超时
     */
    @GetMapping("/timeout")
    public RestResponse timeout() {
        return orderService.timeout();
    }

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public RestResponse create(@RequestBody CreateOrderDTO createOrderDto) {
        return orderService.create(createOrderDto);
    }
}
