package com.zhangxr.springcloud.service.impl;

import com.zhangxr.springcloud.dao.PaymentDao;
import com.zhangxr.springcloud.entities.Payment;
import com.zhangxr.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className PaymentServiceImpl
 * @Description TODO
 * @Author sdzha
 * @Date 2020/10/9 15:18
 * @Version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /*
     * @Resource注解java自带
     * @Autowired注解 spring自带的注解
     */
    @Resource
    private PaymentDao dao;

    @Override
    public int create(Payment payment) {
        return dao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return dao.getPaymentById(id);
    }
}













