package com.qiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟商品库存秒杀
 *
 * @author liq
 * @date 2021/12/23 18:21
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 1. 秒杀-最基本版
     */
    @RequestMapping("/miaosha")
    public void miaoSha() {
        ValueOperations<String, String> operate = stringRedisTemplate.opsForValue();
        String kucun = operate.get("kucun");
        int quantity = Integer.parseInt(kucun);
        if (quantity < 1) {
            System.out.println("没有库存啦");
        }
        quantity -= 1;
        operate.set("kucun", quantity+"");
        System.out.println(String.format("success, 还剩%s个", operate.get("kucun")));
    }

    /**
     * 2. 秒杀-乐观锁
     */
    @RequestMapping("/miaosha2")
    public void miaoSha2() {
        stringRedisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                RedisOperations<String, String> operation = (RedisOperations<String, String>)operations;
                operation.watch("kucun");
                String kucun = operation.opsForValue().get("kucun");
                int quantity = Integer.parseInt(kucun);
                if (quantity < 1) {
                    System.out.println("没有库存啦");
                    return null;
                }
                operation.multi();
                operation.opsForValue().decrement("kucun");
                operation.exec();
                System.out.println(String.format("success, 还剩%s个", operation.opsForValue().get("kucun")));
                return null;
            }
        });
    }

    /**
     * 3. 用lua脚本
     */
}
