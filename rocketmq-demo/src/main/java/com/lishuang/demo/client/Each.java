package com.lishuang.demo.client;

import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.springframework.objenesis.strategy.StdInstantiatorStrategy;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019, 德邦证券股份有限公司
 * FileName: Each
 * Author:   lishuang
 * Date:     2019-06-12 15:11
 * Description: practice of Java8 Stream
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class Each {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        strs.add("Hello");
        strs.add("world");
        strs.add("happy");
        strs.stream().filter(item->item.contains("ell")).forEach(item->{
            System.out.println(item);
        });
    }
}
