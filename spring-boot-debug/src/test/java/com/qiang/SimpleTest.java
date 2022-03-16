package com.qiang;

import com.alibaba.fastjson.JSON;
import com.qiang.config.CarProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liq
 * @date 2022/1/25 16:27
 */
@SpringBootTest
public class SimpleTest {
    @Autowired
    CarProperties carProperties;

    @Test
    @DisplayName("方法1")
    void test1() {
        System.out.println(1);
        System.out.println(JSON.toJSONString(carProperties));
    }

    @Test
    @DisplayName("方法2")
    void test2() {
        System.out.println(2);
    }
}
