package cn.lang.oss.properties;

/**
 * Oss 配置类
 *
 * @author Lang 1102076808@qq.com
 * date 2021-04-08 21:54
 */
public class OssProperties {

	/**
	 * Off-chain domain name
	 */
	private String domain;

	/**
	 * File root directory
	 */
	private String baseDisc;

	/**
	 * File max size
	 */
	private Long maxSize;


	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getBaseDisc() {
		return baseDisc;
	}

	public void setBaseDisc(String baseDisc) {
		this.baseDisc = baseDisc;
	}

	public Long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}
}
