package com.zhangxr.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @className ReadWriteSplittingMain
 * @Description springboot实现读写分离
 * @Author sdzha
 * @Date 2021/1/29 10:13
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ReadWriteSplittingMain {
    public static void main(String[] args) {
        SpringApplication.run(ReadWriteSplittingMain.class,args);
    }
}
