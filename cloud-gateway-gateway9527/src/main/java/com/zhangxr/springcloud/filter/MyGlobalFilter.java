package com.zhangxr.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @className GlobalFilter
 * @Description TODO
 * @Author sdzha
 * @Date 2020/11/18 13:58
 * @Version 1.0
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if(username == null){
            log.info("----> 用户未登录！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    /**
     * Get the order value of this object.
     *  获取此对象的顺序值
     * <p>Higher values are interpreted as lower priority. As a consequence,
     *  值越高，优先级越低。因此，
     * the object with the lowest value has the highest priority (somewhat
     *  值最小的对象具有最高的优先级（有点
     * analogous to Servlet {@code load-on-startup} values).
     * 类似于Servlet{@code load on startup}值）。
     * <p>Same order values will result in arbitrary sort positions for the
     * 相同的顺序值将导致
     * affected objects.
     *  受影响的对象。
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
