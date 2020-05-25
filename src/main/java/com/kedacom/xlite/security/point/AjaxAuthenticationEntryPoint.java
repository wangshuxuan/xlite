package com.kedacom.xlite.security.point;

import com.kedacom.xlite.enums.ResultStatusEnum;
import com.kedacom.xlite.utils.DirectlyRenderUtils;
import com.kedacom.xlite.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 * @author wangshuxuan
 * @date 2020/3/30 10:30
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        DirectlyRenderUtils.renderJson(httpServletResponse,
                new ResultVO<>(ResultStatusEnum.NONLOGIN.getCode()));
    }
}
