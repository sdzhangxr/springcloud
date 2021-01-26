package com.zhangxr.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhangxr.springcloud.entities.SysLog;
import com.zhangxr.springcloud.entities.User;
import com.zhangxr.springcloud.service.UserService;
import com.zhangxr.springcloud.util.LogThreadPool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @className LogController
 * @Description 日志Controller
 * @Author sdzha
 * @Date 2021/1/21 16:59
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getUserInfo")
    public String getUserInfo(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInteger("id");
        User userInfo = userService.getUserInfo(id);

        SysLog sysLog = new SysLog();
        String className = Thread.currentThread().getStackTrace()[1].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String requestData = JSON.toJSONString(jsonObject);
        String responseData = JSON.toJSONString(userInfo);
        sysLog.setClassName(className);
        sysLog.setMethodName(methodName);
        sysLog.setRequestData(requestData);
        sysLog.setResponseData(responseData);
        LogThreadPool.submitLog(sysLog);
        return responseData;
    }
}
