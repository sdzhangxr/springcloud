package com.zhangxr.springcloud.service;

import com.zhangxr.springcloud.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @className AccountService
 * @Description Feign调用账户信息微服务接口
 * @Author sdzha
 * @Date 2021/3/19 15:53
 * @Version 1.0
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {

    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId,
                          @RequestParam("money") BigDecimal money);

}
