package com.qiang.io.rpc;

import com.qiang.io.rpc.biz.User;
import com.qiang.io.rpc.biz.UserService;

/**
 * @author: liq
 * @date: 2021/6/27 21:35
 */
public class Main {
    public static void main(String[] args) {
        UserService userService = (UserService) Stub.getStub(UserService.class);
        User user = userService.getUserById(1);
        System.out.println(user);
    }
}
