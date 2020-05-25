package com.kedacom.xlite.meeting.service;

import com.kedacom.xlite.exception.ApiRequestException;
import com.kedacom.xlite.meeting.api.adapter.ApiAdapter;
import com.kedacom.xlite.meeting.api.result.SsoTokenResult;
import com.kedacom.xlite.meeting.api.result.ValidationTokenResult;
import com.kedacom.xlite.meeting.bean.AccountInfo;
import com.kedacom.xlite.meeting.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * 平台用户apiservice实现
 * @author wangshuxuan
 * @date 2020/3/31 13:28
 */
@Service
public class UserApiServiceImpl implements UserApiService {

    @Resource
    private ApiAdapter apiAdapter;


    /**
     * api登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public String apiLogin(String username, String password) {
        return apiAdapter.login(username, password);
    }

    /**
     * api5登录
     * @param username
     * @param password
     * @param rememberMe
     * @return
     */
    @Override
    public String api5Login(String username, String password, Boolean rememberMe) {
        return apiAdapter.api5Login(username, password ,rememberMe);
    }


    /**
     * 获取user 账号，可以是moid/jid/account/email/e164/mobile
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        AccountInfo accountInfo = apiAdapter.getAccountInfo(username);
        User user = new User();
        BeanUtils.copyProperties(accountInfo, user);
        if (!CollectionUtils.isEmpty(accountInfo.getDepts())) {
            user.setDepartment(accountInfo.getDepts().get(0).getDepartmentName());
        }
        return user;
    }

    /**
     * 校验sso token返回user
     * @param ssoToken
     * @return
     */
    @Override
    public User validationToken(String ssoToken) {
        ValidationTokenResult result = apiAdapter.validationToken(ssoToken);  //result信息不全
        String code = result.getCode();
        if (StringUtils.isNotBlank(code)) {
            if ("10001".equals(code) || "10002".equals(code)) {
                //刷新accountToken
                apiAdapter.refreshAccountToken();
                result = apiAdapter.validationToken(ssoToken);
            }else {
                throw new ApiRequestException(code);
            }
        }
        return getUser(result.getMoid());
    }

    /**
     * 心跳获取ssoToken
     * @param remToken
     * @return
     */
    @Override
    public String keepHeartbeat(String remToken) {

        SsoTokenResult ssoTokenResult = apiAdapter.keepHeartbeat(remToken);
        //防止accountToken失败
        if (StringUtils.isNotBlank(ssoTokenResult.getCode())) {
            if ("10001".equals(ssoTokenResult.getCode()) || "10002".equals(ssoTokenResult.getCode())) {
                //刷新accountToken
                apiAdapter.refreshAccountToken();
                return apiAdapter.keepHeartbeat(remToken).getSsoToken();
            }else {
                throw new ApiRequestException(ssoTokenResult.getCode());
            }
        }
        return ssoTokenResult.getSsoToken();
    }
}
