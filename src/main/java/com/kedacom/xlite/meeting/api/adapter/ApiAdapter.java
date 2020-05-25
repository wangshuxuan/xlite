package com.kedacom.xlite.meeting.api.adapter;

import com.kedacom.xlite.meeting.api.result.*;
import com.kedacom.xlite.meeting.bean.AccountInfo;
import com.kedacom.xlite.security.holder.SSOTokenHolder;
import com.kedacom.xlite.utils.HttpClientUtils;
import com.kedacom.xlite.utils.HttpResult;
import com.kedacom.xlite.utils.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Apiadapter
 * @author wangshuxuan
 * @date 2020/3/31 12:56
 */
@Repository("apiAdapter")
public class ApiAdapter {


    /**
     * weibo真实环境
     */
    private static final String API_HOST = "http://weibo.kedacom.com";
    private static final String OAUTH_CONSUMER_KEY = "6GqRSqtxqjpp";
    private static final String OAUTH_COMSUMER_SECRET = "r8jAhmWcFZuP";

    private String accountToken = "";

    public String getAccountToken() {
        if (StringUtils.isBlank(accountToken)) {
            accountToken = apiAccountToken();
        }
        return accountToken;
    }

    private void encapsulateErrorCode(ErrorCode e){
        String code = e.getCode();
        if (StringUtils.isNotBlank(code)) {
            throw new RuntimeException(code);
        }
    }

    /**
     * 获取accountToken
     * @return
     */
    private String apiAccountToken() {
        String url = API_HOST + "/api5/accountToken";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("oauth_consumer_key", OAUTH_CONSUMER_KEY);
        params.put("oauth_consumer_secret", OAUTH_COMSUMER_SECRET);
        try {
            HttpResult response = HttpClientUtils.doPost(url, params,
                    SSOTokenHolder.getCookies());
            String return_str = response.getResponse();
            AccountToken oauthData = JacksonUtils.fromXmlString(return_str, AccountToken.class);
            encapsulateErrorCode(oauthData);
            return oauthData.getAccountToken();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 强制刷新accountToken
     */
    public void refreshAccountToken() {
        accountToken = apiAccountToken();
    }

    /**
     * 普通登录
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {
        String url = API_HOST + "/api/v1/system/login";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account_token", getAccountToken());
        params.put("username", username);
        params.put("password", password);
        try {
            HttpResult response = HttpClientUtils.doPost(url, params);
            for (String cookie : response.getCookies()) {
                if (cookie.startsWith("SSO_COOKIE_KEY=")) {
                    String ssoToken = cookie.split("; ")[0].substring("SSO_COOKIE_KEY=".length());
                    if (StringUtils.isNoneBlank(ssoToken) && !"null".equalsIgnoreCase(ssoToken)) {
                        return ssoToken;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 普通登录
     * @param username
     * @param password
     * @return
     */
    public String api5Login(String username, String password, boolean rememberMe) {
        String url = API_HOST + "/api5/login";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account_token", getAccountToken());
        params.put("username", username);
        params.put("password", password);
        params.put("rememberMe", rememberMe);
        try {
            HttpResult response = HttpClientUtils.doPost(url, params);
            for (String cookie : response.getCookies()) {
                if (cookie.startsWith("SSO_REMEMBER_ME_COOKIE_KEY=")) {
                    String ssoToken = cookie.split("; ")[0].substring("SSO_REMEMBER_ME_COOKIE_KEY=".length());
                    if (StringUtils.isNoneBlank(ssoToken) && !"null".equalsIgnoreCase(ssoToken)) {
                        return ssoToken;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 根据账号获取account_info
     * @param username
     * @return
     */
    public AccountInfo getAccountInfo(String username) {
        String url = API_HOST + "/api5/ams/getAccountInfo";
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("account_token", getAccountToken());
        params.put("username", username);
        try {
            HttpResult response = HttpClientUtils.doPost(url, params, SSOTokenHolder.getCookies());
            String return_str = response.getResponse();

            AccountInfo accountInfo = JacksonUtils.fromXmlString(return_str, AccountInfo.class);
            encapsulateErrorCode(accountInfo);
            return accountInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验token
     * @param ssoToken
     * @return
     */
    public ValidationTokenResult validationToken(String ssoToken) {
        String url = API_HOST + "/api5/sso/validationToken";
        Map<String, String> params = new HashMap<String, String>();
        params.put("account_token", getAccountToken());
        params.put("ssoToken", ssoToken);
        HttpResult response = null;
        try {
            response = HttpClientUtils.doPost(url, params, SSOTokenHolder.getCookies());
            String return_str = response.getResponse();
            ValidationTokenResult tokenResult = JacksonUtils.json2Bean(return_str,
                    ValidationTokenResult.class);
            //错误返回的是xml 正确返回的是json 特殊处理
            if (tokenResult == null) {
                tokenResult = JacksonUtils.fromXmlString(return_str, ValidationTokenResult.class);
            }
            //encapsulateErrorCode(tokenResult);
            return tokenResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保持心跳返回token
     * @param remToken
     * @return
     */
    public SsoTokenResult keepHeartbeat(String remToken) {

        SsoTokenResult ssoTokenResult = new SsoTokenResult();
        String url = API_HOST + "/api5/users/keepHeartbeat";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account_token", getAccountToken());
        try {
            HttpResult response = HttpClientUtils.doPost(url, params, new String[]{"SSO_REMEMBER_ME_COOKIE_KEY=" + remToken});

            Success success = JacksonUtils.fromXmlString(response.getResponse(), Success.class);
            if (success != null) {
                if (success.isSuccess()) {
                    for (String cookie : response.getCookies()) {
                        if (cookie.startsWith("SSO_COOKIE_KEY=")) {
                            String ssoToken = cookie.split("; ")[0].substring("SSO_COOKIE_KEY=".length());
                            if (StringUtils.isNoneBlank(ssoToken) && !"null".equalsIgnoreCase(ssoToken)) {
                                ssoTokenResult.setSsoToken(ssoToken);
                            }
                        }
                    }
                }else {
                    ssoTokenResult.setCode(success.getCode());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ssoTokenResult;
    }
}
