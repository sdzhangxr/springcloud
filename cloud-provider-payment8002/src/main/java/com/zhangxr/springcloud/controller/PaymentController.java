package com.zhangxr.springcloud.controller;

import com.zhangxr.springcloud.entities.CommonResult;
import com.zhangxr.springcloud.entities.Payment;
import com.zhangxr.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @className PaymentController
 * @Description 支付模块
 * @Author sdzha
 * @Date 2020/10/9 15:24
 * @Version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService service;

    /**
     * @Author sdzha
     * @Description 创建数据
     * @Date 2020/10/9 15:38
     * @Param [payment]
     * @return com.zhangxr.springcloud.entities.CommonResult
     */
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = service.create(payment);
        log.info("****插入结果："+ result);
        if (result > 0){
            return new CommonResult(200,"插入成功！serverPort: " + serverPort,result);
        } else {
            return new CommonResult(444,"插入失败！");
        }
    }

    /**
     * @Author sdzha
     * @Description 查询数据
     * @Date 2020/10/9 15:38
     * @Param [id]
     * @return com.zhangxr.springcloud.entities.CommonResult
     */
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = service.getPaymentById(id);
        log.info("****查询结果：" + payment);
        if (payment != null){
            return new CommonResult(200,"查询成功！serverPort: " + serverPort,payment);
        } else {
            return new CommonResult(444,"无对应记录！");
        }
    }

    /**
     * @Author sdzha
     * @Description 测试手写负载均衡-轮询-算法
     * @Date 2020/11/11 13:46
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    /**
     * @Author sdzha
     * @Description 测试OpenFeign超时控制
     * @Date 2020/11/11 16:51
     * @Param []
     * @return java.lang.String
     */
    @GetMapping(value = "/payment/timeOut")
    public String timeOut(){
        //暂停几秒钟线程
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}













