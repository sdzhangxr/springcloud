package com.zhangxr.springcloud.controller;

import com.zhangxr.springcloud.entity.Order;
import com.zhangxr.springcloud.service.OrderService;
import com.zhangxr.springcloud.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className OrderController
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/19 16:40
 * @Version 1.0
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");
    }

}
