package cn.lang.oss.configuration;


import cn.lang.oss.handler.OssTencentHandler;
import cn.lang.oss.properties.OssTencentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云配置
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-04-08 22:33
 */

@Configuration
@EnableConfigurationProperties({OssTencentProperties.class})
@ConditionalOnProperty(
		prefix = "lang.oss.tencent",
		name = "enabled",
		havingValue = "true"
)
public class OssTencentConfiguration {

	@Autowired
	private OssTencentProperties ossTencentProperties;

	@Bean
	public OssTencentHandler ossTencentHandler(){
		return new OssTencentHandler(ossTencentProperties);
	}

}
