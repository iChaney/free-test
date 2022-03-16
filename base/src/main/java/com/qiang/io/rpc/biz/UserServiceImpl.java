package com.qiang.io.rpc.biz;

/**
 * @author: liq
 * @date: 2021/6/27 21:20
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        return new User("小王", 18);
    }
}
