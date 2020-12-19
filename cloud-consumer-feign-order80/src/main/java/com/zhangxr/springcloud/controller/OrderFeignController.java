package com.zhangxr.springcloud.controller;

import com.zhangxr.springcloud.entities.CommonResult;
import com.zhangxr.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className OrderFeignController
 * @Description TODO
 * @Author sdzha
 * @Date 2020/11/11 16:20
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/timeOut")
    public String timeOut(){
        return paymentFeignService.timeOut();
    }
}
