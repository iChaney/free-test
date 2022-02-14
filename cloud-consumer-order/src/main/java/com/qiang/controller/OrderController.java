package com.qiang.controller;

import com.qiang.pojo.RestResponse;
import com.qiang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/list")
    public RestResponse list() {
//        return restTemplate.getForObject("http://192.168.1.94:8081/course/list", RestResponse.class);
        return orderService.list();
    }
}
