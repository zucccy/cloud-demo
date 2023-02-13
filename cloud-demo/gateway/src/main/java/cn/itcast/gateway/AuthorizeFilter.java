package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: TODO
 * @Author: ChenYun
 * @CreateDate: 2023/2/13 15:55
 * @Version 1.0
 */
//@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1. 获取请求
        ServerHttpRequest request = exchange.getRequest();
        // 2. 获取请求中的参数
        MultiValueMap<String, String> params = request.getQueryParams();
        // 3. 获取参数authorization
        String auth = params.getFirst("authorization");
        // 4. 判断参数值是否为admin
        if ("admin".equals(auth)) {
            // 5. 如果是，则放行
            return chain.filter(exchange);
        }
        // 6. 如果不是，则拦截
        // 6.1 设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 6.2 拦截
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
