package com.kedacom.xlite.meeting.service;


import com.kedacom.xlite.meeting.bean.User;

/**
 * 平台用户apiservice
 * @author wangshuxuan
 * @date 2020/3/31 13:28
 */
public interface UserApiService {

    /**
     * api登录
     * @param username
     * @param password
     * @return
     */
    String apiLogin(String username, String password);

    /**
     * api5登录 长效
     * @param username
     * @param password
     * @param rememberMe
     * @return
     */
    String api5Login(String username, String password, Boolean rememberMe);

    /**
     * 获取user 账号，可以是moid/jid/account/email/e164/mobile
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 校验token 返回user
     * @param ssoToken
     * @return
     */
    User validationToken(String ssoToken);

    /**
     * 心跳获取sso token
     * @param remToken
     * @return
     */
    String keepHeartbeat(String remToken);

}
