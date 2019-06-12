package com.lishuang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketmqDemoApplication {

    String  valeu = "Hello";
    public static void main(String[] args) {
        SpringApplication.run(RocketmqDemoApplication.class, args);
    }

}
