package com.kedacom.xlite.security.holder;

/**
 * SSOTokenHolder
 * @author luocanfeng
 * @date 2018年6月25日
 */
public class
SSOTokenHolder {

	public final static String COOKIE_NAME = "SSO_COOKIE_KEY";

	public final static String REMEMBER_COOKIE_NAME = "SSO_REMEMBER_ME_COOKIE_KEY";

	private static ThreadLocal<String> ssoTokenHolder = new ThreadLocal<String>();
	private static ThreadLocal<String> userMoidHolder = new ThreadLocal<String>();

	public static void clearSsoToken() {
		ssoTokenHolder.set(null);
	}

	public static String getSsoToken() {
		return ssoTokenHolder.get();
	}

	public static void setSsoToken(String ssoToken) {
		ssoTokenHolder.set(ssoToken);
	}

	public static String getCookie() {
		return COOKIE_NAME + "=" + ssoTokenHolder.get();
	}

	public static String[] getCookies() {
		return new String[] {
				getCookie()
		};
	}

	public static void clearUserMoid() {
		userMoidHolder.set(null);
	}

	public static String getUserMoid() {
		return userMoidHolder.get();
	}

	public static void setUserMoid(String userMoid) {
		userMoidHolder.set(userMoid);
	}

}
