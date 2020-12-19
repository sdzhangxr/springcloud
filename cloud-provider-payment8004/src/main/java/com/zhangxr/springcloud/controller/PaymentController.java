package com.zhangxr.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @className PaymentController
 * @Description 支付模块
 * @Author sdzha
 * @Date 2020/10/9 15:24
 * @Version 1.0
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentZk(){
        return "zookeeper: " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}













