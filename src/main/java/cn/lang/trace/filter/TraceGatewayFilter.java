package cn.lang.trace.filter;

import cn.lang.trace.Trace;
import cn.lang.trace.TraceHelper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * TraceGatewayFilter
 * 网关过滤器
 * @author Lang 1102076808@qq.com
 * @date 2022-06-17
 */
public class TraceGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        TraceHelper.clearTrace();
        TraceHelper.genTrace();
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(Trace.TRACE_HEADER, TraceHelper.getTrace().getTraceId()).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
