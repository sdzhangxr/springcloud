package com.zhangxr.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @className MyLoadBalancer
 * @Description 手写负载均衡算法
 * @Author sdzha
 * @Date 2020/11/11 14:10
 * @Version 1.0
 */
public interface MyLoadBalancer {
    ServiceInstance instance(List<ServiceInstance> instances);
}
