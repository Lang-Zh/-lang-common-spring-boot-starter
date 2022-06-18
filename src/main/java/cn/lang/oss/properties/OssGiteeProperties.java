package cn.lang.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Gitee配置类
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-1-03 11:06
 */
@ConfigurationProperties(prefix = "lang.oss.gitee")
public class OssGiteeProperties extends OssProperties{

	/**
	 * Gitee enabled
	 */
	private Boolean enabled;
	/**
	 * Gitee private token
	 */
	private  String accessToken;
	/**
	 * personal space name
	 */
	private  String owner;
	/**
	 * Upload designated warehouse
	 */
	private  String repo;


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRepo() {
		return repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
