package cn.lang.trace.configuration;


import cn.lang.trace.filter.TraceFeignInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * TraceFeignConfiguration
 *
 * @author Lang 1102076808@qq.com
 * date 2022-06-17
 */
@Configuration
@ConditionalOnClass(RequestInterceptor.class)
@ConditionalOnProperty(
        prefix = "lang.trace",
        name = "feign-enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class TraceFeignConfiguration {

    @Bean
    public TraceFeignInterceptor traceFeignInterceptor(){
        return new TraceFeignInterceptor();
    }

}
