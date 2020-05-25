package com.kedacom.xlite.security.service;

import com.kedacom.xlite.meeting.bean.User;
import com.kedacom.xlite.meeting.service.UserApiService;
import com.kedacom.xlite.security.context.SessionUser;
import com.kedacom.xlite.security.holder.SSOTokenHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MyUserDetailsService
 * @author wangshuxuan
 * @date 2020/3/30 16:16
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private static final Boolean rememberMe = true;

    @Resource
    private UserApiService userApiService;

    @Override
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
        String remToken = null;
        try {
            //api 登录获取ssoToken
            //ssoToken = userApiService.apiLogin(username, password);
            remToken = userApiService.api5Login(username, password, rememberMe);
            if (StringUtils.isBlank(remToken)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        SSOTokenHolder.setSsoToken(userApiService.keepHeartbeat(remToken)); // 登录成功后，将SSO Token存放到线程上下文中
        SessionUser sessionUser = (SessionUser) loadUserByUsername(username);
        sessionUser.setToken(remToken);
        return sessionUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userApiService.getUser(username);
        SessionUser returnUser = new SessionUser();
        BeanUtils.copyProperties(user, returnUser);
        return returnUser;
    }
}
