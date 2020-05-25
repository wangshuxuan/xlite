package com.kedacom.xlite.utils;

import com.kedacom.xlite.security.holder.SSOTokenHolder;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (用一句话描述类的主要功能)
 * @author huangchunhua
 * @date 2014-6-4
 */
public class CookieUtils {

	private final static String cookieName = "SSO_COOKIE_KEY";
	private final static String remCookieName = "SSO_REMEMBER_ME_COOKIE_KEY";
	public static final int COOKIE_MAX_AGE = 7 * 24 * 3600;
	public static final int COOKIE_HALF_HOUR = 30 * 60;

	public static String getTokenCookie(HttpServletRequest request) {
		String ssoToken = getCookieValue(request, cookieName);
		if (!StringUtils.isEmpty(ssoToken) && !"null".equalsIgnoreCase(ssoToken)) {
			SSOTokenHolder.setSsoToken(ssoToken);
		}
		return StringUtils.isEmpty(cookieName) ? "" : ssoToken;
	}

    public static String getRemTokenCookie(HttpServletRequest request) {
        String ssoToken = getCookieValue(request, remCookieName);
        if (!StringUtils.isEmpty(ssoToken) && !"null".equalsIgnoreCase(ssoToken)) {
            SSOTokenHolder.setSsoToken(ssoToken);
        }
        return StringUtils.isEmpty(remCookieName) ? "" : ssoToken;
    }

	public static void cancelCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookiePath, String cookieDomain) {
		try {
			Cookie cookie = new Cookie(cookieName, null);
			cookie.setMaxAge(0);
			cookie.setPath(cookiePath);
			cookie.setDomain(cookieDomain);
			response.addCookie(cookie);
		} catch (Exception e) {

		}
	}

	/**
	 * 设置Cookie
	 * @param token
	 * @param maxAge
	 * @param request
	 * @param response
	 */
	public static void setCookie(String token, int maxAge, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, token);
		cookie.setMaxAge(maxAge);

		cookie.setPath(getCookiePath(request));
		// 取URL顶级域名
		String pattern = getCookieDomain(request);
		if (StringUtils.hasLength(pattern)) {
			cookie.setDomain(pattern);
		}
		cookie.setSecure(false);
		response.addCookie(cookie);
	}

	public static String getCookiePath(HttpServletRequest request) {
		String contextPath = "/";
		return contextPath;
	}

	/**
	 * 取URL顶级域名
	 * @return
	 */
	public static String getCookieDomain(HttpServletRequest request) {
		String url = request.getRequestURL().toString();

		return getCookieDomain(url);
	}

	public static String PATTERN_L2DOMAIN = "(?<=http://|\\.)[^.]*?\\.(com.cn|com|cn|net.cn|net|org.cn|org|biz|info|cc|tv)";
	public static String PATTERN_IP = "(\\d*\\.){3}\\d*";

	public static String getCookieDomain(String url) {
		/* 以IP形式访问时，返回IP */
		Pattern ipPattern = Pattern.compile(PATTERN_IP);
		Matcher matcher = ipPattern.matcher(url);
		if (matcher.find()) {
			return matcher.group();
		}

		/* 以域名访问时，返回二级域名 */
		Pattern pattern = Pattern.compile(PATTERN_L2DOMAIN);
		matcher = pattern.matcher(url);
		if (matcher.find()) {
			return matcher.group();

		}

		return null;
	}

	/**
	 * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isEmpty(cookies)) {
			return null;
		}
		Cookie cookie = null;
		for (Cookie c : cookies) {
			if (name.equals(c.getName())) {
				cookie = c;
				break;
			}
		}
		return cookie;
	}

	/**
	 * 根据Cookie名称直接得到Cookie值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 移除cookie
	 * @param request
	 * @param response
	 * @param name 这个是名称，不是值
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		if (null == name) {
			return;
		}
		Cookie cookie = getCookie(request, name);
		if (null != cookie) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * 添加一条新的Cookie，可以指定过期时间(单位：秒)
	 * @param response
	 * @param name
	 * @param value
	 * @param maxValue
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxValue) {
		if (StringUtils.isEmpty(name)) {
			return;
		}
		if (null == value) {
			value = "";
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxValue != 0) {
			cookie.setMaxAge(maxValue);
		} else {
			cookie.setMaxAge(COOKIE_HALF_HOUR);
		}
		response.addCookie(cookie);
		try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加一条新的Cookie，默认30分钟过期时间
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, COOKIE_HALF_HOUR);
	}

	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	public static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (!ArrayUtils.isEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

}
