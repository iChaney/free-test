package com.qiang.feign;

import com.qiang.pojo.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liq
 * @date 2022/2/14 16:44
 */
@FeignClient(value = "payment-provider")
public interface PaymentFeignClient {

    // 这里写的是provider的路径
    @RequestMapping("/course/list")
    RestResponse list();

    @GetMapping("/course/timeout")
    RestResponse timeout();
}
