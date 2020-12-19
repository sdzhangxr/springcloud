package com.zhangxr.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @className RestTemplateConfig
 * @Description 配置类
 * @Author sdzha
 * @Date 2020/10/9 17:25
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced //使用@LoadBalanced注解开启RestTemplate负载均衡功能。
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
