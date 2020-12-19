package com.zhangxr.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhangxr.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className OrderHystrixController
 * @Description TODO
 * @Author sdzha
 * @Date 2020/11/12 15:59
 * @Version 1.0
 */
@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "paymentInfo_global_fallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;


    /**
     * 正常访问
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result=paymentHystrixService.paymentInfo_ok(id);
        return result;
    }

    /**
     * 超时
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_Handler",
            commandProperties = {
                @HystrixProperty(
                        name = "execution.isolation.thread.timeoutInMilliseconds",
                        value = "5000"
                )
            }
    )
//    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        String result=paymentHystrixService.paymentInfo_timeout(id);
        return result;

    }

    /**
     * @Author sdzha
     * @Description 兜底方法
     * @Date 2020/11/13 14:29
     * @Param []
     * @return java.lang.String
     */
    public String paymentInfo_timeout_Handler(@PathVariable("id") Integer id){
        return "80端口兜底方法！";
    }

    /**
     * @Author sdzha
     * @Description 全局通用兜底方法
     * @Date 2020/11/13 14:29
     * @Param []
     * @return java.lang.String
     */
    public String paymentInfo_global_fallbackMethod(){
        return "通用兜底方法！";
    }

}
