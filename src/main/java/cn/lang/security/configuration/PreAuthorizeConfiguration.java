package cn.lang.security.configuration;

import cn.lang.security.aspect.PreAuthorizeAspect;
import cn.lang.security.properties.PreAuthorizeProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName : PreAuthorizeHandler
 * description : 鉴权配置类
 * @author : Lang
 * date: 2022-06-18
 */
@Configuration
@EnableConfigurationProperties({PreAuthorizeProperties.class})
@ConditionalOnProperty(
        prefix = "lang.authorize",
        name = "enabled",
        havingValue = "true"
)
public class PreAuthorizeConfiguration {

    @Bean
    public PreAuthorizeAspect preAuthorizeAspect(){
        return new PreAuthorizeAspect();
    }

}
