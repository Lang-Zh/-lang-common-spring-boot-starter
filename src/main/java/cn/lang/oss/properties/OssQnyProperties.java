package cn.lang.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 七牛云配置类
 *
 * @author Lang 1102076808@qq.com
 * @date 2021-04-08 22:06
 */
@ConfigurationProperties(prefix = "lang.oss.qny")
public class OssQnyProperties extends OssProperties{

	/**
	 * qiniuyun enabled
	 */
	private Boolean enabled;

	/**
	 * AccessKey
	 */
	private  String accessKey;
	/**
	 * SecretKey
	 */
	private  String secretKey;
	/**
	 *  Storage space name
	 */
	private  String bucket;


	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
