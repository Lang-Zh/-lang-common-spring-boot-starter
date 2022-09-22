package cn.lang.global.configuration;

import cn.lang.global.handler.GlobalExceptionHandler;
import cn.lang.global.properties.GlobalExceptionProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author Lang 1102076808@qq.com
 * description GlobalExceptionConfiguration
 * date 2022-06-18
 */
@Configuration
@EnableConfigurationProperties({GlobalExceptionProperties.class})
@ConditionalOnProperty(
        prefix = "lang.global-exception",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class GlobalExceptionConfiguration {
    @Bean
    public GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }
}
