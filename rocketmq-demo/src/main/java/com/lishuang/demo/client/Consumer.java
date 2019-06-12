package com.lishuang.demo.client;

import com.lishuang.demo.constants.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Copyright (C), 2019, 德邦证券股份有限公司
 * FileName: Consumer
 * Author:   lishuang
 * Date:     2019-06-12 14:35
 * Description: Consumer
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class Consumer {
    public void consume(String clientName) throws  Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consumer");
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR);
        // 从第几个开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("test_quick_topic", "*");

        consumer.registerMessageListener(
                new MessageListenerConcurrently() {
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                        for(MessageExt ext: msgs){
                            System.out.println("Client"+clientName+" consume:"+new String(ext.getBody()));

                        }
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                }
        );
        consumer.start();
        System.out.println("consumer end");
    }
    public static void main(String[] args) {

    }
}
