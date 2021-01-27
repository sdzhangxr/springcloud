package com.zhangxr.springcloud.util.cache.service.impl;

import com.zhangxr.springcloud.entities.SysLog;
import com.zhangxr.springcloud.util.cache.mapper.LogMapper;
import com.zhangxr.springcloud.util.cache.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className LogServiceImpl
 * @Description 日志处理接口实现类
 * @Author sdzha
 * @Date 2021/1/21 17:38
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public void addLog(SysLog sysLog) {
        logMapper.addLog(sysLog);
    }
}
