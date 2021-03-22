package com.zhangxr.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @className MyBatisConfig
 * @Description Mybatis整合
 * @Author sdzha
 * @Date 2021/3/22 10:30
 * @Version 1.0
 */
@Configuration
@MapperScan({"com.zhangxr.springcloud.dao"})
public class MyBatisConfig {
}
