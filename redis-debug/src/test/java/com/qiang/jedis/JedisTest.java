package com.qiang.jedis;

import com.qiang.ops.HyperLogLogService;
import com.qiang.ops.StringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liq
 * @date 2021/12/2 14:58
 */
@SpringBootTest
public class JedisTest {
    @Autowired
    HyperLogLogService hyperLogLogService;
    @Autowired
    StringService stringService;

    /**
     * 删除中控的门店活动
     */
    @Test
    @DisplayName("删除中控的门店活动")
    public void deleteHotelActivity() {
        stringService.deleteHotelActivity("07085", "", "", "");
    }
    @Test
    public void testString() {
        stringService.setValues("k2", "888");
    }

    @Test
    public void test() {
        hyperLogLogService.pipelined();
    }
}
