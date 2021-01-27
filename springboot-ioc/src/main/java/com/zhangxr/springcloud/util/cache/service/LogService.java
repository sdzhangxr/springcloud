package com.zhangxr.springcloud.util.cache.service;

import com.zhangxr.springcloud.entities.SysLog;

/**
 * @className LogService
 * @Description 日志处理接口
 * @Author sdzha
 * @Date 2021/1/21 17:37
 * @Version 1.0
 */
public interface LogService {
    void addLog(SysLog sysLog);
}
