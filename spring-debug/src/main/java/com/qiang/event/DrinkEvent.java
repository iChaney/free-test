package com.qiang.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author: liq
 * @date: 2021/9/27 22:11
 */
public class DrinkEvent extends ApplicationEvent {
    private String quantity;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public DrinkEvent(Object source, String quantity) {
        super(source);
        System.out.println("创建了一个喝水事件");
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
}
