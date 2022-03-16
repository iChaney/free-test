package com.qiang.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: liq
 * @date: 2021/9/27 22:20
 */
@Component
public class DrinkListener implements ApplicationListener<DrinkEvent> {
    @Override
    public void onApplicationEvent(DrinkEvent event) {
        System.out.println("接受到喝水事件");
        System.out.println(String.format("需要喝%s杯水", event.getQuantity()));
    }
}
