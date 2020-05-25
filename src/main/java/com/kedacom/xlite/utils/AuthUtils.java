package com.kedacom.xlite.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证头获取token
 * @author wangshuxuan
 * @date 2020/4/3 15:21
 */
public class AuthUtils {
    private final static String cookieName = "SSO_COOKIE_KEY";
    private final static String remCookieName = "SSO_REMEMBER_ME_COOKIE_KEY";

    /**
     * 获取ssoToken
     * @param request
     * @return
     */
    public static String getSsoTokenFromAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String[] auStrs = authorization.split(";");
        for (String value : auStrs) {
            if (value.startsWith(cookieName)) {
                return value.substring((cookieName + "=").length());
            }
        }
        return null;
    }

    /**
     * 获取remember ssoToken
     * @param request
     * @return
     */
    public static String getRemSsoTokenFromAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String[] auStrs = authorization.split(";");
        for (String value : auStrs) {
            if (value.startsWith(remCookieName)) {
                return value.substring((remCookieName + "=").length());
            }
        }
        return null;
    }
}
