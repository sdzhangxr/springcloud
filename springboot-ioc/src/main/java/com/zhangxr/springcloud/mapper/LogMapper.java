package com.zhangxr.springcloud.mapper;

import com.zhangxr.springcloud.entities.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className LogMapper
 * @Description 日志处理Mapper
 * @Author sdzha
 * @Date 2021/1/21 17:40
 * @Version 1.0
 */
@Mapper
public interface LogMapper {

    @Insert("insert into sys_log(" +
            "   class_name," +
            "   method_name," +
            "   request_data," +
            "   response_data," +
            "   request_time," +
            "   server_ip)" +
            "values(" +
            "   #{className}," +
            "   #{methodName}," +
            "   #{requestData}," +
            "   #{responseData}," +
            "   #{requestTime}," +
            "   #{serverIp}" +
            ")")
    void addLog(SysLog sysLog);
}
