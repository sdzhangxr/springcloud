package com.zhangxr.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className MyLB
 * @Description 手写负载均衡算法
 * @Author sdzha
 * @Date 2020/11/11 14:13
 * @Version 1.0
 */
@Component
public class MyLB implements MyLoadBalancer {

    /**
     * 对于Java中的运算操作，例如自增或自减，若没有进行额外的同步操作，在多线程环境下就是线程不安全的。
     *
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getAndIncrement(){
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current > 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("next--->"+next);
        System.out.println("atomicInteger--->"+atomicInteger);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = getAndIncrement() % instances.size();
        return instances.get(index);
    }
}
