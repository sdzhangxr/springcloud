package com.zhangxr.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

/**
 * @className GatewayConfig
 * @Description 路由配置类
 * @Author sdzha
 * @Date 2020/11/17 15:17
 * @Version 1.0
 */
//@Configuration
public class GatewayConfig {

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        routes.route("payment_routh2",
//                r ->r.path("/payment/lb")
//                        .uri("http://localhost:8001/payment/lb")).build();
//        return routes.build();
//    }
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
