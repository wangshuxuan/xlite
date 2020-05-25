package com.kedacom.xlite.security.context;

import com.kedacom.xlite.meeting.bean.User;

/**
 * SessionUser
 * @author wangshuxuan
 * @date 2020/3/30 15:10
 */
public class SessionUser extends User {

    private String token; // sso token

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
