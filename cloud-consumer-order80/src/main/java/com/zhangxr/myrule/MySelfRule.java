package com.zhangxr.myrule;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className MySelfRule
 * @Description 自定义负载均衡算法--随机
 * @Author sdzha
 * @Date 2020/11/11 10:23
 * @Version 1.0
 */
@Configuration
public class MySelfRule {
    @Bean
    public RandomRule randomRule(){
        //定义为随机
        return new RandomRule();
    }
}
