package cn.lang.oss.configuration;


import cn.lang.oss.handler.OssQnyHandler;
import cn.lang.oss.properties.OssQnyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云配置
 *
 * @author Lang 1102076808@qq.com
 * date 2021-04-08 22:33
 */

@Configuration
@EnableConfigurationProperties({OssQnyProperties.class})
@ConditionalOnProperty(
		prefix = "lang.oss.qny",
		name = "enabled",
		havingValue = "true"
)
public class OssQnyConfiguration {

	@Autowired
	private OssQnyProperties ossQnyProperties;

	@Bean
	public OssQnyHandler qnyOss(){
		return new OssQnyHandler(ossQnyProperties);
	}

}
