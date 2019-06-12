package com.lishuang.demo.client;

import com.lishuang.demo.constants.Const;
import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * Copyright (C), 2019, 德邦证券股份有限公司
 * FileName: Producer
 * Author:   lishuang
 * Date:     2019-06-11 14:51
 * Description: 简单的消息客户端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class Producer {
    public static void main(String[] args) {
        //设置生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("test_quick");
        producer.setNamesrvAddr(Const.NAMESRV_ADDR);
        //producer.setRetryTimesWhenSendAsyncFailed(3);
        try {

            producer.start();

            Message message = new Message("test_quick_topic", "tagA", "hello".getBytes());
            SendResult result = producer.send(message);
            System.out.println(result.getMsgId());
            producer.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void consume() throws  Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consumer");
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR);
        // 从第几个开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("test_quick_topic", "*");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                 for(MessageExt ext:msgs){
                     System.out.println(new java.lang.String(ext.getBody()));
                 }
                 return  ConsumeOrderlyStatus.SUCCESS;
            }
        });
    }
}
