package com.qiang.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author liq
 * @date 2021/9/3 14:13
 */
public class OrderEvent extends ApplicationEvent {
    // 订单号
    private String orderNo;

    public OrderEvent(Object source, String orderNo) {
        super(source);
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
