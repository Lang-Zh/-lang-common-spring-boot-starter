package cn.lang.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 又拍云配置类
 *
 * @author Lang 1102076808@qq.com
 * @date 2021-04-08 22:04
 */
@ConfigurationProperties(prefix = "lang.oss.upy")
public class OssUpyProperties extends OssProperties{


	/**
	 * youpaiyun enabled
	 */
	private Boolean enabled;

	/**
	 * server name
	 */
	private String bucket;

	/**
	 * Operator name
	 */
	private String username;

	/**
	 * Operator password
	 */
	private String password;


	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
