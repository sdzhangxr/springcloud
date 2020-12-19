package com.zhangxr.springcloud.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @className JPAUtils
 * @Description 解决实体管理器工厂的浪费资源和耗时问题
 *  通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的实体管理器工厂对象
 *  第一次访问getEntityManager方法：经过静态代码块创建一个factory对象，再调用方法创建一个EntityManager对象
 *  第二次方法getEntityManager方法：直接通过一个已经创建好的factory对象，创建EntityManager对象
 * @Author sdzha
 * @Date 2020/12/18 15:09
 * @Version 1.0
 */
public class JpaUtils {
    
    public static EntityManagerFactory factory;

    static {
        //1.加载配置文件，创建entityManagerFactory
        factory = Persistence.createEntityManagerFactory("myJpa");
    }
    /***
     * @Author sdzha
     * @Description 获取EntityManager对象
     * @Date 2020/12/18 15:14
     * @Param []
     * @return javax.persistence.EntityManager
     */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
