package com.kedacom.xlite.security.handler;

import com.kedacom.xlite.config.WxAccountConfig;
import com.kedacom.xlite.enums.ResultStatusEnum;
import com.kedacom.xlite.exception.BusinessException;
import com.kedacom.xlite.model.XUser;
import com.kedacom.xlite.security.context.SessionUser;
import com.kedacom.xlite.service.XUserService;
import com.kedacom.xlite.utils.*;
import com.kedacom.xlite.vo.ResultVO;
import com.kedacom.xlite.vo.XUserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功handler
 * @author wangshuxuan
 * @date 2020/3/30 10:34
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private WxAccountConfig wxAccountConfig;
    @Resource
    private XUserService xUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        SessionUser sessionUser = (SessionUser) authentication.getPrincipal();

        XUser xUser = xUserService.findUserByMoid(sessionUser.getMoid());
        if (xUser == null) {
            //为新openId
            String authCode = request.getParameter("code");
            //向微信请求openId
            String openId = getOpenId(authCode);
            if (StringUtils.isBlank(openId)) {
                throw new BusinessException("请求openId失败");
            }
            //插入
            XUser user = new XUser();
            user.setMoid(sessionUser.getMoid());
            user.setOpenId(openId);
            xUserService.saveUser(user);
        }

        XUserVO xUserVO = new XUserVO();
        xUserVO.setUserName(sessionUser.getName());
        xUserVO.setMoid(sessionUser.getMoid());
        xUserVO.setDepartment(sessionUser.getDepartment());
        xUserVO.setMobilephone(sessionUser.getMobile());
        xUserVO.setUserImg(sessionUser.getPortrait256());
        xUserVO.setEmail(sessionUser.getEmail());
        xUserVO.setToken(sessionUser.getToken());

        DirectlyRenderUtils.renderJson(response,
                JacksonUtils.bean2Json(new ResultVO<>(ResultStatusEnum.SUCCESS.getCode(), xUserVO)));

    }

    /**
     * 第三方微信请求
     * @param authCode
     * @return
     */
    private String getOpenId(String authCode) throws IOException {
        Map<String, String> params = new HashMap<>();

        params.put("appid", wxAccountConfig.getAppId());
        params.put("secret", wxAccountConfig.getSecret());
        params.put("js_code", authCode);
        params.put("grant_type", wxAccountConfig.getGrantType());

        HttpResult httpResult = HttpClientUtils.doGet(wxAccountConfig.getUrl(), params);
        logger.debug("请求openId返回结果：{}", httpResult.getResponse());
        WxAccount wxAccount = JacksonUtils.json2Bean(httpResult.getResponse(), WxAccount.class);

        return wxAccount != null ? wxAccount.getOpenId() : null;
    }
}
