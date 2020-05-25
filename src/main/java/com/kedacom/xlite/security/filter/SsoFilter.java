package com.kedacom.xlite.security.filter;

import com.kedacom.xlite.exception.SSOException;
import com.kedacom.xlite.security.point.AjaxAuthenticationEntryPoint;
import com.kedacom.xlite.security.service.SsoTokenValidateService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * sso filter
 * @author wangshuxuan
 * @date 2020/3/30 12:29
 */
public class SsoFilter implements Filter {

    private AuthenticationManager authenticationManager;

    private SsoTokenValidateService ssoTokenValidateService;

    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    public SsoFilter(AuthenticationManager authenticationManager,
                     SsoTokenValidateService ssoTokenValidateService,
                     AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint) {

        this.authenticationManager = authenticationManager;
        this.ssoTokenValidateService = ssoTokenValidateService;
        this.ajaxAuthenticationEntryPoint = ajaxAuthenticationEntryPoint;
    }

    private List<String> filterUrl = Collections.singletonList("/userManage/login");// 过滤，不需拦截的URL

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestUrl = httpRequest.getRequestURL().toString();

        if (isFilter(httpRequest)) {
            Authentication authentication = null;
            try {
                authentication = ssoTokenValidateService.autoLogin(httpRequest, httpResponse);
                authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // sso 认证失败
                ajaxAuthenticationEntryPoint.commence(httpRequest, httpResponse, new SSOException(e.getMessage()));
                return;
            }
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }

    private boolean isFilter(HttpServletRequest request) {
        boolean isFilter = true;
        String requestUrl = request.getRequestURL().toString();

        for (String url : filterUrl) {
            if (url.contains("/**")) {
                url = url.substring(0, url.indexOf("/**"));
                if (requestUrl.contains(url)) {
                    isFilter = false;
                    break;
                }
            } else {
                int i = requestUrl.lastIndexOf(url);
                if (requestUrl.contains(url) && (requestUrl.lastIndexOf(url) + url.length() == requestUrl.length())) {
                    isFilter = false;
                    break;
                }
            }
        }
        return isFilter;
    }

    @Override
    public void destroy() {

    }
}
