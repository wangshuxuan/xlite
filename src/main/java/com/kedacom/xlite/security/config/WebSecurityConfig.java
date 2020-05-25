package com.kedacom.xlite.security.config;

import com.kedacom.xlite.security.filter.SsoFilter;
import com.kedacom.xlite.security.handler.AuthenticationFailHandler;
import com.kedacom.xlite.security.handler.AuthenticationSuccessHandler;
import com.kedacom.xlite.security.handler.MyLogoutSuccessHandler;
import com.kedacom.xlite.security.point.AjaxAuthenticationEntryPoint;
import com.kedacom.xlite.security.provider.LoginAuthenticationProvider;
import com.kedacom.xlite.security.provider.SSOAuthenticationProvider;
import com.kedacom.xlite.security.service.MyUserDetailsService;
import com.kedacom.xlite.security.service.SsoTokenValidateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Security 核心配置类
 * @author wangshuxuan
 * @date 2020/3/30 10:07
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;
    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailHandler authenticationFailHandler;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private MyUserDetailsService myUserDetailsService;
    @Resource
    private SsoTokenValidateService ssoTokenValidateService;


    @Bean
    LoginAuthenticationProvider loginAuthenticationProvider(){
        return new LoginAuthenticationProvider(myUserDetailsService);
    }

    @Bean
    SSOAuthenticationProvider ssoAuthenticationProvider(){
        return new SSOAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                .and()
                .formLogin()
                .loginProcessingUrl("/userManage/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHandler)
                .and()
                .logout()
                .logoutUrl("/userManage/logout")
                .permitAll()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .addFilterAfter(new SsoFilter(authenticationManager(), ssoTokenValidateService, ajaxAuthenticationEntryPoint),
                        UsernamePasswordAuthenticationFilter.class
                )
                .csrf()
                .disable();
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider().
//    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {

        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices
        // 因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        //authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return new ProviderManager(Arrays.asList(loginAuthenticationProvider(),
                ssoAuthenticationProvider()));
    }

}
