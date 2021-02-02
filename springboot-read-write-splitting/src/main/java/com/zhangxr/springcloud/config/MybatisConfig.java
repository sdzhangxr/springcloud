package com.zhangxr.springcloud.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @className MybatisConfig
 * @Description 现在有三个数据源，我们要为事务管理器和mybatis手动指定一个明确的数据源
 * @Author sdzha
 * @Date 2021/1/29 11:10
 * @Version 1.0
 */
@Configuration
public class MybatisConfig {

    //依赖注入 自定义的 路由数据源
    @Resource(name = "myRoutingDataSource")
    private DataSource myRoutingDataSource;

    /**
     * @Author sdzha
     * @Description mybatis指定数据源和sql文件位置
     * @Date 2021/1/29 14:36
     * @Param []
     * @return org.apache.ibatis.session.SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/*.xml")
        );
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * @Author sdzha
     * @Description 事务管理器指定数据源
     * @Date 2021/1/29 14:38
     * @Param []
     * @return org.springframework.transaction.PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(myRoutingDataSource);
    }
}
