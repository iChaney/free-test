package com.qiang.controller;

import com.qiang.pojo.RestResponse;
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

    @RequestMapping("/list")
    public RestResponse list() {
        return restTemplate.getForObject("http://192.168.1.94:8001/course/list", RestResponse.class);
    }
}
