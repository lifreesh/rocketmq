package com.lishuang.demo.client;

/**
 * Copyright (C), 2019, 德邦证券股份有限公司
 * FileName: ConsumerClient1
 * Author:   lishuang
 * Date:     2019-06-12 15:26
 * Description: CosumerClient1
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class ConsumerClient1 {
    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        try {
            consumer.consume("client1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
