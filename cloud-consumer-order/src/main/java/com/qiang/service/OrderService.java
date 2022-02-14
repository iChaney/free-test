package com.qiang.service;

import com.qiang.pojo.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liq
 * @date 2022/2/14 16:44
 */
@Component
@FeignClient(value = "payment-provider")
public interface OrderService {

    // 这里写的是provider的路径
    @RequestMapping("/course/list")
    RestResponse list();
}
