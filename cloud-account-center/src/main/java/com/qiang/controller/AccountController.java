package com.qiang.controller;

import com.qiang.pojo.RestResponse;
import com.qiang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author liq
 * @date 2022/2/16 13:38
 */
@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/reduce")
    public RestResponse reduce(@PathParam("userId") String userId, @PathParam("money") Integer money) {
        return accountService.reduce(userId, money);
    }
}
