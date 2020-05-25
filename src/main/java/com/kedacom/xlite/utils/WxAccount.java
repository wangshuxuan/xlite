package com.kedacom.xlite.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信相关信息
 * @author wangshuxuan
 * @date 2020/4/2 14:20
 */
public class WxAccount {

    @JsonProperty("openid")
    private String openId;
    @JsonProperty("session_key")
    private String sessionKey;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
