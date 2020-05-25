package com.kedacom.xlite.security.service;

import com.kedacom.xlite.meeting.bean.User;
import com.kedacom.xlite.meeting.service.UserApiService;
import com.kedacom.xlite.security.bean.SSOAuthenticationToken;
import com.kedacom.xlite.security.context.SessionUser;
import com.kedacom.xlite.security.holder.SSOTokenHolder;
import com.kedacom.xlite.utils.AuthUtils;
import com.kedacom.xlite.utils.CookieUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * token 校验
 * @author wangshuxuan
 * @date 2020/3/31 15:44
 */
@Service
public class SsoTokenValidateService {

    @Resource
    private UserApiService userApiService;

    private static final String key = "kedacom-ssoKey-GVVxVQMjhNcccKYviQk7MocaWQnoJVz785KG";
    /**
     * 验证token 统一处理 获取cookie
     * 如果存在remember sso cookie---keepHeartbeat获取到新的ssoToken
     * 如果不存在 取正常 sso cookie
     * @param request
     * @param response
     * @return
     */
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        String remToken = AuthUtils.getRemSsoTokenFromAuthorization(request);
        String ssoToken = "";
        if (StringUtils.hasLength(remToken)) {
            //心跳获取 ssoToken
            ssoToken = userApiService.keepHeartbeat(remToken);
        }else {
            //单点
            ssoToken = AuthUtils.getSsoTokenFromAuthorization(request);
            if (!StringUtils.hasLength(ssoToken)) {
                return null;
            }
        }
        //ThreadLocal
        SSOTokenHolder.setSsoToken(ssoToken);

        User user = userApiService.validationToken(ssoToken);

        SessionUser sessionUser = new SessionUser();
        BeanUtils.copyProperties(user, sessionUser);

        //List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority("anonymousUser"));
        sessionUser.setToken(remToken);
        //sessionUser.getAuthorities().add(new SimpleGrantedAuthority("0"));
        SSOAuthenticationToken auth = new SSOAuthenticationToken(key, sessionUser, sessionUser.getAuthorities());
        auth.setDetails(sessionUser);

        return auth;
    }
}
