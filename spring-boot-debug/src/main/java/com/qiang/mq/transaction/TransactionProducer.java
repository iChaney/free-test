package com.qiang.mq.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;

/**
 * @author liq
 * @date 2022/3/16 17:36
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("my_group");
        producer.setNamesrvAddr("122.112.205.177:9876");

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                System.out.printf("%s的状态为%s%n", msg.getTags(), arg);
                return (LocalTransactionState)arg;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.printf("check%s%n", msg.getTags());
                // 如果是unkown状态需要检查业务是否执行, 如果执行了就丢弃消息, 没执行就提交
                String orderID = new String(msg.getBody());
                // 根据订单号查询业务
                if(Integer.parseInt(orderID) % 2 == 1) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
            }
        });

        producer.start();

        for (int i = 0; i < 3; i++) {
            Message message = new Message("t_topic", "tag"+i, new String(i+"").getBytes(StandardCharsets.UTF_8));
            // 执行本地事务
            /**
             * tage0 回滚
             * tag1 提交
             * tag2 检查后提交
             */
            LocalTransactionState state = LocalTransactionState.UNKNOW;
            if(i == 0) {
                state = LocalTransactionState.ROLLBACK_MESSAGE;
            } else if(i == 1){
                state = LocalTransactionState.COMMIT_MESSAGE;
            }
            TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(message, state);
            System.out.printf("%s%n", transactionSendResult);
        }

        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}
