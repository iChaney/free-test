package com.qiang.controller;

import com.qiang.annotations.ApiRequestBody;
import com.qiang.dto.HelloDTO;
import com.qiang.event.OrderEvent;
import com.qiang.exception.BizExeception;
import com.qiang.service.HelloService;
import com.qiang.vo.HelloVO;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liq
 * @date 2021/9/3 14:23
 */
@RestController
public class HelloController {
    @Resource
    ApplicationContext applicationContext;
    @Resource
    HelloService helloService;

    @RequestMapping("/hello")
    public HelloVO hello(@ApiRequestBody HelloDTO helloDTO) {
        throw new BizExeception("业务处理异常");
        /*HelloVO helloVO = new HelloVO();
        helloVO.setName(helloDTO.getName());
        helloVO.setAge(18);
        helloVO.setClothColor("red black");
        return helloVO;*/
    }

    @RequestMapping("/hello/name")
    public String helloName(@RequestParam("name") String name) {
        return helloService.sayHello(name);
    }



    @RequestMapping("/order/create")
    public String createOrder(String orderNo) {
        applicationContext.publishEvent(new OrderEvent(this, orderNo)); // 发布时间
        return "success";
    }
}
