package cn.lang.oss.configuration;


import cn.lang.oss.handler.OssHelloHandler;
import cn.lang.oss.properties.OssHelloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gitee配置
 *
 * @author Lang 1102076808@qq.com
 * date 2022-1-06 11:33
 */

@Configuration
@EnableConfigurationProperties({OssHelloProperties.class})
@ConditionalOnProperty(
        prefix = "lang.oss.hello",
        name = "enabled",
        havingValue = "true"
)
public class OssHelloConfiguration {

    @Autowired
    private OssHelloProperties ossHelloProperties;

    @Bean
    public OssHelloHandler ossHelloHandler() {
        return new OssHelloHandler(ossHelloProperties);
    }

}
