package com.zhangxr.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className ConfigController
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/9 23:10
 * @Version 1.0
 */
@RestController
@RefreshScope //自动刷新
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public String configInfo(){
        return configInfo;
    }
}
