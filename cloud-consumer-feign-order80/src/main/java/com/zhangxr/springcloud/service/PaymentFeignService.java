package com.zhangxr.springcloud.service;

import com.zhangxr.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className PaymentFeignService
 * @Description OpenFeign配置接口
 * @Author sdzha
 * @Date 2020/11/11 16:16
 * @Version 1.0
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/timeOut")
    public String timeOut();

}
