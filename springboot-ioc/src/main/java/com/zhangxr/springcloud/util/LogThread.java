package com.zhangxr.springcloud.util;

import com.zhangxr.springcloud.entities.SysLog;
import com.zhangxr.springcloud.util.cache.service.LogService;
import com.zhangxr.springcloud.util.cache.service.impl.LogServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @className LogThread
 * @Description 异步处理日志
 * @Author sdzha
 * @Date 2021/1/21 17:26
 * @Version 1.0
 */
@Slf4j
public class LogThread implements Runnable{

    private SysLog sysLog;

    private static LogService logService = SpringContextUtils.getBean(LogServiceImpl.class);

    public LogThread(SysLog sysLog) {
        this.sysLog = sysLog;
    }

    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep(3);
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String requestTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            sysLog.setServerIp(hostAddress);
            sysLog.setRequestTime(requestTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("----------["+ Thread.currentThread().getName() +"] 线程被调用");
        logService.addLog(sysLog);
    }
}
