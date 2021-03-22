package com.zhangxr.springcloud.service;

import com.zhangxr.springcloud.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @className StorageService
 * @Description Feign调用库存微服务接口
 * @Author sdzha
 * @Date 2021/3/19 15:51
 * @Version 1.0
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId,
                          @RequestParam("count") Integer count);

}
