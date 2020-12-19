package com.zhangxr.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @className OrderZKController
 * @Description TODO
 * @Author sdzha
 * @Date 2020/11/10 15:07
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderZKController {
    private final static String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/order/info")
    public String order(){
        String s = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return s;
    }

}
