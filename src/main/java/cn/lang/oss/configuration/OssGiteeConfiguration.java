package cn.lang.oss.configuration;


import cn.lang.oss.handler.OssGiteeHandler;
import cn.lang.oss.properties.OssGiteeProperties;
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
@EnableConfigurationProperties({OssGiteeProperties.class})
@ConditionalOnProperty(
        prefix = "lang.oss.gitee",
        name = "enabled",
        havingValue = "true"
)
public class OssGiteeConfiguration {

    @Autowired
    private OssGiteeProperties ossGiteeProperties;

    @Bean
    public OssGiteeHandler ossGiteeHandler() {
        return new OssGiteeHandler(ossGiteeProperties);
    }

}
