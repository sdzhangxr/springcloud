package com.zhangxr.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.netty.util.Timeout;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @className FlowLimitController
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/11 17:19
 * @Version 1.0
 */
@RestController
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA(){
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "---------testA";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "---------testB";
    }

    /**
     * @Author sdzha
     * @Description 热点Key规则
     * @Date 2021/3/15 10:24
     * @Param [p1, p2]
     * @return java.lang.String
     */
    @GetMapping("/hotKey")
    @SentinelResource(value = "hotKey",blockHandler = "dealHotKey")
    public String hotKey(@RequestParam(value = "p1",required = false) String p1,
                         @RequestParam(value = "p2",required = false) String p2){
        return "hotKey";
    }

    public String dealHotKey(String p1, String p2, BlockException e){
        return "dealHotKey";
    }
}
