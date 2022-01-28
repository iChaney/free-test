package com.qiang.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author liq
 * @date 2021/9/3 14:15
 */
@Component
public class OrderListener implements ApplicationListener<OrderEvent> {
    @Override
    public void onApplicationEvent(OrderEvent event) {
        String date = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), ZoneId.of("+8"))
                                   .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(String.format("收到订单变动事件, 时间:%s, 订单号:%s", date, event.getOrderNo()));
        System.out.println("do biz ...");
    }
}
