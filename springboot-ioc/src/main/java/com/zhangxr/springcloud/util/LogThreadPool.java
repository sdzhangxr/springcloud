package com.zhangxr.springcloud.util;

import com.zhangxr.springcloud.entities.SysLog;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className LogThreadPool
 * @Description 处理日志线程池
 * @Author sdzha
 * @Date 2021/1/21 17:08
 * @Version 1.0
 */
@Slf4j
public class LogThreadPool {

    private static ExecutorService executorService = Executors.newFixedThreadPool(PublicConstant.threadPoolNum);
    public static void submitLog(SysLog sysLog){
        executorService.submit(new LogThread(sysLog));
        log.info("-------- 异步处理日志入库 --------");
    }
}
