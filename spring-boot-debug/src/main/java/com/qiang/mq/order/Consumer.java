package com.qiang.mq.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author: liq
 * @date: 2022/3/12 13:27
 */
public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my_group");

        // Specify name server addresses.
        consumer.setNamesrvAddr("122.112.205.177:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);


        // Subscribe one more more topics to consume.
        consumer.subscribe("my_topic", "*");
        // 默认负载均衡模式
//        consumer.setMessageModel(MessageModel.BROADCASTING);

        // Register callback to execute on arrival of messages fetched from brokers.
        // MessageListenerOrderly 用单线程去处理绑定同一个orderId的消息
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                for (MessageExt msg : msgs) {
                    String m = new String(msg.getBody());
                    System.out.println(m);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
