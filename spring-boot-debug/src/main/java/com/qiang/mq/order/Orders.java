package com.qiang.mq.order;

import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liq
 * @date: 2022/3/14 18:33
 */
public class Orders {
    public static class Order {
        int id;  // 订单号
        String step; // 步骤
        public Order(int id, String step) {
            this.id = id;
            this.step = step;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", step='" + step + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }

    static List<Order> getOrders() {
        List<Order> list = Lists.newArrayList();
        list.add(new Order(120, "开始"));
        list.add(new Order(120, "下单"));
        list.add(new Order(121, "开始"));
        list.add(new Order(120, "支付"));
        list.add(new Order(122, "开始"));
        list.add(new Order(120, "完成"));
        return list;
    }
}
