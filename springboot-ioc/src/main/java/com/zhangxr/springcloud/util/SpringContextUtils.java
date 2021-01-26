package com.zhangxr.springcloud.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @className SpringContextUtils
 * @Description spring容器管理类
 * @Author sdzha
 * @Date 2021/1/21 16:39
 * @Version 1.0
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    /**
     * @Author sdzha
     * @Description 上下文实例
     * @Date 2021/1/21 16:41
     * @Param
     * @return
     */
    private static ApplicationContext applicationContext;

    /**
     * @Author sdzha
     * @Description 重写ApplicationContextAware中的setApplicationContext
     *              设置上下文实例
     * @Date 2021/1/21 16:42
     * @Param [applicationContext]
     * @return void
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @Author sdzha
     * @Description 获取上下文实例
     * @Date 2021/1/21 16:45
     * @Param []
     * @return org.springframework.context.ApplicationContext
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * @Author sdzha
     * @Description 通过name获取Bean
     * @Date 2021/1/21 16:46
     * @Param [name]
     * @return java.lang.Object
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * @Author sdzha
     * @Description 通过class获取Bean
     * @Date 2021/1/21 16:48
     * @Param [clazz]
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return getApplicationContext().getBean(clazz);
        }catch (Exception e) {
            return null;
        }
    }

    /**
     * @Author sdzha
     * @Description 通过name，以及class指定返回
     * @Date 2021/1/21 16:50
     * @Param [name, clazz]
     * @return T
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name,clazz);
    }
}




















