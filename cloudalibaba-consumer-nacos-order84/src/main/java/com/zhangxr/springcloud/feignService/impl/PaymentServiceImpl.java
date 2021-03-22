package com.zhangxr.springcloud.feignService.impl;

import com.zhangxr.springcloud.entities.CommonResult;
import com.zhangxr.springcloud.entities.Payment;
import com.zhangxr.springcloud.feignService.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @className PaymentServiceImpl
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/17 14:22
 * @Version 1.0
 */
@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回！！！");
    }
}
