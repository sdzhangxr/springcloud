package com.zhangxr.springcloud.aop;

import com.zhangxr.springcloud.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @className DataSourceAop
 * @Description 默认情况下，所有查询走从库，增删改走主库。
 *              我们通过方法名来区分操作类型，用Aop配置来实现
 * @Author sdzha
 * @Date 2021/1/29 11:25
 * @Version 1.0
 */
@Aspect
@Component
public class DataSourceAop {

    /**
     * @Author sdzha
     * @Description 不用master注解、select或者get起头的方法 查询从表
     * @Date 2021/1/29 14:16
     * @Param []
     * @return void
     */
    @Pointcut("!@annotation(com.zhangxr.springcloud.annotation.Master) " +
            "&& (execution(* com.zhangxr.springcloud.service..*.select*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.get*(..))) ")
    public void readPointcut(){}

    /**
     * @Author sdzha
     * @Description 使用master注解或者增删改数据库时操作使用主表
     * @Date 2021/1/29 14:18
     * @Param []
     * @return void
     */
    @Pointcut("@annotation(com.zhangxr.springcloud.annotation.Master) " +
            "|| execution(* com.zhangxr.springcloud.service..*.insert*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.add*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.update*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.edit*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.delete*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.remove*(..)) ")
    public void writePointcut(){}

    @Before("readPointcut()")
    public void read(){
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write(){
        DBContextHolder.master();
    }
}
