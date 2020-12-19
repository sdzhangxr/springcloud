package com.zhangxr.springcloud.service.impl;

import com.zhangxr.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @className PaymentFallbackService
 * @Description 统一为接口里面的方法进行异常处理
 * @Author sdzha
 * @Date 2020/11/16 14:03
 * @Version 1.0
 */
//@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "PaymentFallbackService --fall-- paymentInfo_ok";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "PaymentFallbackService --fall-- paymentInfo_timeout";
    }
}
