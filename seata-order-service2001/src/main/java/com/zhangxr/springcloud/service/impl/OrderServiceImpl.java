package com.zhangxr.springcloud.service.impl;

import com.zhangxr.springcloud.dao.OrderDao;
import com.zhangxr.springcloud.entity.Order;
import com.zhangxr.springcloud.service.AccountService;
import com.zhangxr.springcloud.service.OrderService;
import com.zhangxr.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className OrderServiceImpl
 * @Description 下订单Service实现类
 * @Author sdzha
 * @Date 2021/3/19 15:50
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    //下订单与修改订单状态
    @Resource
    private OrderDao orderDao;

    //调用微服务减库存
    @Resource
    private StorageService storageService;

    //调用微服务减账户金额
    @Resource
    private AccountService accountService;

    /**
     * @Author sdzha
     * @Description 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * @Date 2021/3/19 16:41
     * @Param
     * @return
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order){
        log.info("----->下订单开始了");

        //新建订单
        log.info("----->开始新建订单");
        orderDao.create(order);
        log.info("----->完成新建订单");

        //扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count start");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减Count end");

        //扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money start");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减Money end");

        //修改订单状态，从零到1代表已经完成
        log.info("----->修改订单状态开始 start");
        orderDao.update(order.getUserId(),0);
        log.info("----->修改订单状态结束 end");

        log.info("----->下订单结束了");
    }
}
