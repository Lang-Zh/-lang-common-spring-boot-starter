package cn.lang.oss.configuration;

import cn.lang.oss.handler.OssUpyHandler;
import cn.lang.oss.properties.OssUpyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 又拍云配置类
 * @author Lang 1102076808@qq.com
 * date 2021-04-08 22:13
 */
@Configuration
@EnableConfigurationProperties({OssUpyProperties.class})
@ConditionalOnProperty(
		prefix = "lang.oss.upy",
		name = "enabled",
		havingValue = "true"
)
public class OssUpyConfiguration {


	@Autowired
	private OssUpyProperties ossUpyProperties;

	@Bean
	public OssUpyHandler upyOss(){
		return new OssUpyHandler(ossUpyProperties);
	}

}
