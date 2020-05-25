package com.kedacom.xlite.security.handler;

import com.kedacom.xlite.enums.ResultStatusEnum;
import com.kedacom.xlite.utils.AuthUtils;
import com.kedacom.xlite.utils.CookieUtils;
import com.kedacom.xlite.utils.DirectlyRenderUtils;
import com.kedacom.xlite.utils.JacksonUtils;
import com.kedacom.xlite.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败handler
 * @author wangshuxuan
 * @date 2020/3/30 10:34
 */
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        DirectlyRenderUtils.renderJson(response,
                JacksonUtils.bean2Json(new ResultVO<>(ResultStatusEnum.FAILURE.getCode(), "用户名或密码错误")));
    }
}
