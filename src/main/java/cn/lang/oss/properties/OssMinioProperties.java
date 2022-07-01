package cn.lang.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Minio配置类
 *
 * @author Lang 1102076808@qq.com
 * date 2021-10-06 11:06
 */
@ConfigurationProperties(prefix = "lang.oss.minio")
public class OssMinioProperties extends OssProperties{

	/**
	 * minio enabled
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
