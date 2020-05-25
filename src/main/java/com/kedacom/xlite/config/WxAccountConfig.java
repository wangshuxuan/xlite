package com.kedacom.xlite.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "wx")
public class WxAccountConfig {
	
	/**
	 * id
	 */
	private String appId;
	
	/**
	 * 密钥
	 */
	private String secret;
	
	/**
	 * url
	 */
	private String url;
	
	/**
	 * grantType
	 */
	private String grantType;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	
	
}
