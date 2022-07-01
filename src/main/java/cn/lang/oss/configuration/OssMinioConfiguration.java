package cn.lang.oss.configuration;


import cn.lang.oss.handler.OssMinioHandler;
import cn.lang.oss.properties.OssMinioProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio配置
 *
 * @author Lang 1102076808@qq.com
 * date 2021-10-06 11:33
 */

@Configuration
@EnableConfigurationProperties({OssMinioProperties.class})
@ConditionalOnProperty(
		prefix = "lang.oss.minio",
		name = "enabled",
		havingValue = "true"
)
public class OssMinioConfiguration {

	@Autowired
	private OssMinioProperties ossMinioProperties;


	@Bean
	public OssMinioHandler minioOssHandler(){
		return new OssMinioHandler(ossMinioProperties);
	}


}
