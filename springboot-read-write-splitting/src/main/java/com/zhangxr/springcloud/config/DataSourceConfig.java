package com.zhangxr.springcloud.config;

import com.zhangxr.springcloud.bean.MyRoutingDataSource;
import com.zhangxr.springcloud.enums.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @className DataSourceConfig
 * @Description 配置3个数据源，一个主数据源，一个从数据源，一个路由数据源
 *              前两个数据源都是为了生成第三个数据源，后续我们只用最后一个路由数据源
 * @Author sdzha
 * @Date 2021/1/29 10:39
 * @Version 1.0
 */
@Configuration
public class DataSourceConfig {

    /**
     * @Author sdzha
     * @Description 主数据源
     * @Date 2021/1/29 14:31
     * @Param []
     * @return javax.sql.DataSource
     */
    @Bean("masterDataSource")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * @Author sdzha
     * @Description 从数据源
     * @Date 2021/1/29 14:31
     * @Param []
     * @return javax.sql.DataSource
     */
    @Bean("slaveDataSource")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * @Author sdzha
     * @Description 路由数据源
     * @Date 2021/1/29 14:31
     * @Param [masterDataSource, slaveDataSource]
     * @return javax.sql.DataSource
     */
    @Bean
    @Primary
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slaveDataSource") DataSource slaveDataSource){
        //新建map对象，放入两个数据源
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBTypeEnum.MASTER,masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE,slaveDataSource);
        //TODO
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSource);
        return myRoutingDataSource;
    }
}














