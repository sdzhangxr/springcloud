package com.zhangxr.springcloud.controller;

/**
 * @className Comer
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/15 16:08
 * @Version 1.0
 */

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhangxr.springcloud.entities.CommonResult;
import com.zhangxr.springcloud.entities.Payment;
import com.zhangxr.springcloud.feignService.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    public String SERVICE_URL;

    //-------------------------使用 ribbon 完成调用其他微服务 start-------------------------
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//没有配置
    //@SentinelResource(value = "fallback",fallback = "fallbackHandler")//只配置fallback,负责业务违规,java代码运行异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")//只配置blockHandler,只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "fallbackHandler",
                    exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id, CommonResult.class,id);
        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    public CommonResult<Payment> fallbackHandler(@PathVariable Long id,Throwable e){
        return new CommonResult<>(444,"fallbackHandler, "+e.getMessage());
    }

    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException e){
        return new CommonResult<>(444,"fallbackHandler, "+e.getMessage());
    }

    //-------------------------使用 ribbon 完成调用其他微服务 end-------------------------


    //-------------------------使用 feign 完成调用其他微服务 start-------------------------
    @Resource
    private PaymentService paymentService;
    @GetMapping("/feign/{id}")
    public CommonResult<Payment> feign(@PathVariable("id") Long id){
        CommonResult<Payment> paymentCommonResult = paymentService.paymentSQL(id);
        return paymentCommonResult;
    }
    //-------------------------使用 feign 完成调用其他微服务 end-------------------------
}

