package cn.lang.security.configuration;

import cn.lang.security.aspect.PreAuthorizeAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : PreAuthorizeHandler
 * @Description : 鉴权配置类
 * @author : Lang
 * @Date: 2022-06-18
 */
@Configuration
@ConditionalOnProperty(
        prefix = "lang.authorize",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class PreAuthorizeConfiguration {

    @Bean
    public PreAuthorizeAspect preAuthorizeAspect(){
        return new PreAuthorizeAspect();
    }

}
