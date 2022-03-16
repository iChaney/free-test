package com.qiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liq
 * @date 2021/12/2 15:18
 */
@SpringBootApplication
@MapperScan("com.qiang.mapper")
public class RedisDebugApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisDebugApplication.class, args);
    }
}
