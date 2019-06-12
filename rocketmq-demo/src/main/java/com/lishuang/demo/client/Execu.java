package com.lishuang.demo.client;

import com.lishuang.demo.constants.Const;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.ExecutorService;

/**
 * Copyright (C), 2019, 德邦证券股份有限公司
 * FileName: Execu
 * Author:   lishuang
 * Date:     2019-06-12 11:04
 * Description: 测试ExceutorService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class Execu {

    public static void main(String[] args) {
        //设置生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("test_quick");
        producer.setNamesrvAddr(Const.NAMESRV_ADDR);
        producer.setRetryTimesWhenSendAsyncFailed(3);
        try {

            producer.start();

            for(int i =0 ;i<=1000;i++) {
                Message message = new Message("test_quick_topic", "tagA", ("hello"+i).getBytes());
                SendResult result = producer.send(message);
                System.out.println(result.getMsgId());
                Thread.sleep(100);
            }
            producer.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
