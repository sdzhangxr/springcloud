package com.zhangxr.springcloud.bean;

import com.zhangxr.springcloud.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className DBContextHolder
 * @Description 通过ThreadLocal将数据源设置到每个线程的上下文
 * @Author sdzha
 * @Date 2021/1/29 10:58
 * @Version 1.0
 */
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType){
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get(){
        return contextHolder.get();
    }

    public static void master(){
        set(DBTypeEnum.MASTER);
        System.out.println("------切换到master数据源------");
    }

    public static void slave(){
        set(DBTypeEnum.SLAVE);
        System.out.println("------切换到slave数据源------");
    }
}
