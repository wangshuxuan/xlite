package com.kedacom.xlite.security.handler;

import com.kedacom.xlite.enums.ResultStatusEnum;
import com.kedacom.xlite.utils.DirectlyRenderUtils;
import com.kedacom.xlite.utils.JacksonUtils;
import com.kedacom.xlite.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理
 * @author wangshuxuan
 * @date 2020/3/30 10:59
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        DirectlyRenderUtils.renderJson(httpServletResponse,
                JacksonUtils.bean2Json(new ResultVO<>(ResultStatusEnum.SUCCESS.getCode())));

    }
}
