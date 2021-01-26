package com.zhangxr.springcloud.entities;

import java.util.Date;

/**
 * @className SysLog
 * @Description 日志实体
 * @Author sdzha
 * @Date 2021/1/21 17:22
 * @Version 1.0
 */
public class SysLog {
    private int id;
    private String className;
    private String methodName;
    private String requestData;
    private String responseData;
    private String  requestTime;
    private String serverIp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String  getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", requestData='" + requestData + '\'' +
                ", responseData='" + responseData + '\'' +
                ", requestTime=" + requestTime +
                ", serverIp='" + serverIp + '\'' +
                '}';
    }
}
