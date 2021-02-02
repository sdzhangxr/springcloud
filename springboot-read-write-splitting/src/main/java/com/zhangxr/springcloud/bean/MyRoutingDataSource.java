package com.zhangxr.springcloud.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @className myRoutingDataSource
 * @Description 路由获取
 * @Author sdzha
 * @Date 2021/1/29 10:52
 * @Version 1.0
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
