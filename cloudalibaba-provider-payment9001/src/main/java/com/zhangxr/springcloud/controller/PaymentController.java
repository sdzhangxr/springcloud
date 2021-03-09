package com.zhangxr.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className PaymentController
 * @Description nacos 业务类
 * @Author sdzha
 * @Date 2021/3/9 22:14
 * @Version 1.0
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String prot;

    @GetMapping(value = "/payment/{id}")
    public String paymentInfo(@PathVariable("id") int id){
        return "nacos registry, serverProt: " + prot + ",\tid: " +id;
    }
}
