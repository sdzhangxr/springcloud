package com.zhangxr.springcloud.service;

import com.zhangxr.springcloud.entity.Order;

/**
 * @className OrderService
 * @Description 下订单Service
 * @Author sdzha
 * @Date 2021/3/19 15:49
 * @Version 1.0
 */
public interface OrderService {
    void create(Order order);
}
