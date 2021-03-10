package com.zhangxr.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className ConfigClient3377
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/9 23:08
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientMain3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3377.class,args);
    }
}
