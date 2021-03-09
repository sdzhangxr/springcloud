package com.zhangxr.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @className OrderController
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/9 22:41
 * @Version 1.0
 */
@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/{id}")
    public String orderInfo(@PathVariable("id") int id){
        return restTemplate.getForObject(serverURL+"/payment/"+id,String.class);
    }

}










