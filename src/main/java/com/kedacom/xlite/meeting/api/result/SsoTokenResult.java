package com.kedacom.xlite.meeting.api.result;

/**
 * @author wangshuxuan
 * @date 2020/4/3 13:45
 */
public class SsoTokenResult extends ErrorCode {

    private String ssoToken;

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }
}
