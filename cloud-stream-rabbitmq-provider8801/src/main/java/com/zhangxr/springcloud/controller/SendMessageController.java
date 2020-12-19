package com.zhangxr.springcloud.controller;

import com.zhangxr.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className SendMessageController
 * @Description TODO
 * @Author sdzha
 * @Date 2020/11/23 16:06
 * @Version 1.0
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider provider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return provider.send();
    }
}
