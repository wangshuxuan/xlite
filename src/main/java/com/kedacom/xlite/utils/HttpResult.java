package com.kedacom.xlite.utils;

/**
 * HttpResult
 * @author luocanfeng
 * @date 2016年12月15日
 */
public class HttpResult {

	private String response = null;
	private String[] cookies = null;

	public HttpResult() {
	}

	public HttpResult(String response) {
		this.response = response;
	}

	public HttpResult(String response, String[] cookies) {
		this.response = response;
		this.cookies = cookies;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String[] getCookies() {
		return cookies;
	}

	public void setCookies(String[] cookies) {
		this.cookies = cookies;
	}

}
