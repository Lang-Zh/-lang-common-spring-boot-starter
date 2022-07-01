package cn.lang.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云 COS 配置类
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-07-01
 */
@ConfigurationProperties(prefix = "lang.oss.tencent")
public class OssTencentProperties extends OssProperties{

	/**
	 * tencent cos enabled
	 */
	private Boolean enabled;

	/**
	 * SECRETID
	 */
	private  String secretId;
	/**
	 * SECRETKEY
	 */
	private  String secretKey;
	/**
	 *  Storage space name
	 */
	private  String bucket;


	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
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
