package com.zhangxr.springcloud.dao;

import com.zhangxr.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    /**
     * @Author sdzha
     * @Description 创建数据
     * @Date 2020/10/9 15:00
     * @Param [payment] 实体类
     * @return int sql执行成功标识
     */
    int create(Payment payment);

    /**
     * @Author sdzha
     * @Description 通过id获取信息
     * @Date 2020/10/9 15:01
     * @Param [id]
     * @return com.zhangxr.springcloud.entities.Payment
     */
    Payment getPaymentById(@Param("id") Long id);
}
