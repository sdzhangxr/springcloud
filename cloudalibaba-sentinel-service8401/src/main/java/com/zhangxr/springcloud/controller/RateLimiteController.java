package com.zhangxr.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhangxr.springcloud.entities.CommonResult;
import com.zhangxr.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className RateLimiteController
 * @Description TODO
 * @Author sdzha
 * @Date 2021/3/15 11:16
 * @Version 1.0
 */
@RestController
public class RateLimiteController {

    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流OK","sentinel001");
    }

    public CommonResult handlerException(BlockException e){
        return new CommonResult(4000,e.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按url限流OK","sentinel002");
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
                    blockHandlerClass = CustomerBlockHandler.class,
                    blockHandler = "handlerException2"
    )
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按统一自定义","sentinel002");
    }
}
