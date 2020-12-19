package com.zhangxr.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhangxr.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className PaymentController
 * @Description TODO
 * @Author sdzha
 * @Date 2020/11/12 15:20
 * @Version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    /**
     * @Author sdzha
     * @Description 测试Hystrix正常访问
     * @Date 2020/11/12 15:23
     * @Param [id]
     * @return java.lang.String
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_ok(id);
        log.info("*******result:" +result);
        return  result;
    }

    /**
     * @Author sdzha
     * @Description 测试Hystrix超时访问
     * @Date 2020/11/12 15:23
     * @Param [id]
     * @return java.lang.String
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_time(@PathVariable("id") Integer id){
        String result=paymentService.paymentInfo_timeout(id);
        log.info("*******result:" +result);
        return  result;
    }

    //---------------------------------熔断---------------------------

    @GetMapping("/payment/hystrix/circuit/{id}")
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaher_fallbackMethod",
            commandProperties = {
                    //开启断路器
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    //请求峰值
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                    //时间范围
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
                    //错误率
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
            }
    )
    public String paymentCircuitBreaher(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("id 不能为负数！");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + uuid;
    }

    public String paymentCircuitBreaher_fallbackMethod(@PathVariable("id") Integer id){
        return "fallback--id 不能为负数！";
    }
}













