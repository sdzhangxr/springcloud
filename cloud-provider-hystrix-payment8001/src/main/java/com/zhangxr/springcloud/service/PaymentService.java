package com.zhangxr.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @className PaymentService
 * @Description TODO
 * @Author sdzha
 * @Date 2020/11/12 14:47
 * @Version 1.0
 */
@Service
public class PaymentService {
    /**
     * @Author sdzha
     * @Description 测试Hystrix正常访问
     * @Date 2020/11/12 14:48
     * @Param [id]
     * @return void
     */
    public String paymentInfo_ok(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_ok, id :"+id+"\t"+"成功";
    }

    /**
     * @Author sdzha
     * @Description  测试Hystrix超时访问
     *      本方法出现超时、异常等问题后，用HystrixCommand指定兜底方法
     * @Date 2020/11/12 14:51
     * @Param [id]
     * @return java.lang.String
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_Handler",
        commandProperties = {
                @HystrixProperty(
                        name = "execution.isolation.thread.timeoutInMilliseconds",
                        value = "5000"
                )
        }
    )
    public String paymentInfo_timeout(Integer id){
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+
                " paymentInfo_timeout, id :"+id+"\t"+"耗时（秒): "+time;
    }

    /**
     * @Author sdzha
     * @Description 兜底方法
     * @Date 2020/11/13 10:41
     * @Param [id]
     * @return java.lang.String
     */
    public String paymentInfo_timeout_Handler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+
                " paymentInfo_timeout, id :"+id+"\t"+"兜底方法";
    }

}
