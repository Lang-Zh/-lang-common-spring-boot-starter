package cn.lang.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云 oss 配置类
 *
 * @author Lang 1102076808@qq.com
 * date 2022-07-02
 */
@ConfigurationProperties(prefix = "lang.oss.aliyun")
public class OssAliyunProperties extends OssProperties{

	/**
	 * tencent cos enabled
	 */
	private Boolean enabled;

	/**
	 * SECRETID
	 */
	private  String accessKeyId;
	/**
	 * SECRETKEY
	 */
	private  String accessKeySecret;

	private String endpoint;
	/**
	 *  Storage space name
	 */
	private  String bucket;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
}
