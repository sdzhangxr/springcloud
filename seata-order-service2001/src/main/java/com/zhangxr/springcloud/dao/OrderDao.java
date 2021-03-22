package com.zhangxr.springcloud.dao;

import com.zhangxr.springcloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @className OrderDao
 * @Description 订单模块DAO
 * @Author sdzha
 * @Date 2021/3/19 15:34
 * @Version 1.0
 */
@Mapper
public interface OrderDao
{
    //新建订单
    void create(Order order);

    //修改订单状态，从0改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
