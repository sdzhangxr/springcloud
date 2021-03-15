package com.zhangxr.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zhangxr.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className CustomerBlockHandler
 * @Description 自定义限流处理逻辑
 * @Author sdzha
 * @Date 2021/3/15 14:16
 * @Version 1.0
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException e){
        return new CommonResult(444,"统一处理handler,handlerException---1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"统一处理handler,handlerException---2");
    }
}














