package com.zhangxr.springcloud.controller;

import com.zhangxr.springcloud.entities.CommonResult;
import com.zhangxr.springcloud.entities.Payment;
import com.zhangxr.springcloud.lb.MyLB;
import com.zhangxr.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @className OrderController
 * @Description 订单模块controller
 * @Author sdzha
 * @Date 2020/10/9 17:23
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderController {
    //单服务写死
//    private static final String PAYMENT_URL = "http://localhost:8001";

    //通过在eureka上注册过的微服务名称调用
    private static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";
    /**
     * 发现报错了，现在对外暴露的不再是地址和端口，只认微服务名称了，
     * 可是微服务并不知道下面有几个，找不到这个主机名称，
     * 需要使用@LoadBalanced注解开启RestTemplate负载均衡功能。
     */

    @Resource
    private RestTemplate restTemplate;

    /**
     * @Author sdzha
     * @Description 订单模块创建数据
     *      使用restTemplate的postForObject方法实现调用，返回值为JSON
     * @Date 2020/10/10 9:38
     * @Param [payment]
     * @return com.zhangxr.springcloud.entities.CommonResult
     */
    @PostMapping(value = "/consumer/payment/create")
    public CommonResult create(Payment payment){
        log.info("****订单模块创建数据");
        /*
         * postForObject 传参在请求体中，访问的接口要用注解@RequestBody从请求体中获取
         */
        CommonResult commonResult =
                restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }

    /**
     * @Author sdzha
     * @Description 订单模块查询数据
     * @Date 2020/10/10 9:38
     * @Param [id]
     * @return com.zhangxr.springcloud.entities.CommonResult
     */
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        log.info("****订单模块查询数据");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    /**
     * @Author sdzha
     * @Description 订单模块查询数据
     *      使用restTemplate的  方法实现调用，返回值为JSON
     * @Date 2020/11/11 9:51
     * @Param [id]
     * @return com.zhangxr.springcloud.entities.CommonResult
     */
    @RequestMapping(value = "/consumer/payment/getForEntity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity =
                restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }else {
            return new CommonResult(444,"操作失败！");
        }
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private MyLoadBalancer loadBalancer;
    /**
     * @Author sdzha
     * @Description 手写负载均衡算法
     * @Date 2020/11/11 14:35
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        //获取微服务架构中名为CLOUD-PROVIDER-SERVICE的微服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        //如果为null，返回null；
        if (instances == null || instances.size() <= 0) return null;
        //获取处理业务的微服务
        ServiceInstance instance = loadBalancer.instance(instances);
        //获取url
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
















