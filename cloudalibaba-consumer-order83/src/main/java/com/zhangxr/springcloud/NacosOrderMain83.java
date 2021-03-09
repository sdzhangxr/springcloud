package com.zhangxr.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className NacosOrderMain83
 * @Description nacos消费者主启动类
 * @Author sdzha
 * @Date 2021/3/9 22:37
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain83.class,args);
    }
}
