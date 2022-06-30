package cn.lang.trace.configuration;

import cn.lang.trace.filter.TraceGatewayFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TraceGatewayConfiguration
 *
 * @author Lang 1102076808@qq.com
 * date 2022-06-17
 */
@Configuration
@ConditionalOnClass(GlobalFilter.class)
@ConditionalOnProperty(
        prefix = "lang.trace",
        name = "gateway-enabled",
        havingValue = "true"
)
public class TraceGatewayConfiguration {

    @Bean
    public TraceGatewayFilter traceGatewayFilter(){
        return new TraceGatewayFilter();
    }

}
