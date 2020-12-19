package com.zhangxr.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className FeignConfig
 * @Description OpenFeign日志打印功能
 * @Author sdzha
 * @Date 2020/11/11 17:21
 * @Version 1.0
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }
}
