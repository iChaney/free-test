package com.qiang.mq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author: liq
 * @date: 2022/3/14 18:04
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("my_group");
        // Specify name server addresses.
        producer.setNamesrvAddr("122.112.205.177:9876");
        //Launch the instance.
        producer.start();

        List<Orders.Order> orders = Orders.getOrders();
        for (Orders.Order order : orders) {
            int id = order.getId() % 10;
            Message message = new Message("my_topic", "order_" + id, order.toString().getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                /**
                 * 一个broker有4个queue
                 *
                 * 将同一订单的操作落到同一个queue中, 在同一个queue中的步骤是有序的
                 */
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    int i = ((int) arg) % mqs.size();
                    return mqs.get(i);
                }
            }, id);
            System.out.printf("%s%n", sendResult);
        }
        //server shutdown
        producer.shutdown();
    }
}
