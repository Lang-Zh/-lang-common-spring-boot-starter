package cn.lang.oss.configuration;


import cn.lang.oss.handler.OssAliyunHandler;
import cn.lang.oss.properties.OssAliyunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云配置
 *
 * @author Lang 1102076808@qq.com
 * date 2022-04-08 22:33
 */

@Configuration
@EnableConfigurationProperties({OssAliyunProperties.class})
@ConditionalOnProperty(
		prefix = "lang.oss.aliyun",
		name = "enabled",
		havingValue = "true"
)
public class OssAliyunConfiguration {

	@Autowired
	private OssAliyunProperties ossAliyunProperties;

	@Bean
	public OssAliyunHandler ossAliyunHandler(){
		return new OssAliyunHandler(ossAliyunProperties);
	}

}
