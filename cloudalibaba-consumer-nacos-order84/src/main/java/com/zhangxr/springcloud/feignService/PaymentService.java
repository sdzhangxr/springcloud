package com.zhangxr.springcloud.feignService;

import com.zhangxr.springcloud.entities.CommonResult;
import com.zhangxr.springcloud.entities.Payment;
import com.zhangxr.springcloud.feignService.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className PaymentService
 * @Description 使用Feign实现微服务之间的调用
 * @Author sdzha
 * @Date 2021/3/17 14:11
 * @Version 1.0
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentServiceImpl.class)
public interface PaymentService {
    @GetMapping(value="/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
