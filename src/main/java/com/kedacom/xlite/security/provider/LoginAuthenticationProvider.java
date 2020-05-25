package com.kedacom.xlite.security.provider;

import com.kedacom.xlite.security.service.UserDetailsService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * LoginAuthenticationProvider
 * @author wangshuxuan
 * @date 2020/3/30 16:49
 */
public class LoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


    // ~ Instance fields
    // ================================================================================================

    private UserDetailsService userDetailsService;

    public LoginAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // ~ Methods
    // ========================================================================================================

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;

        try {
            String password = (String) authentication.getCredentials();
            loadedUser = getUserDetailsService().loadUserByUsername(username, password);// 区别在这里
        } catch (UsernameNotFoundException notFound) {
            throw notFound;
        }  catch (Exception repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }

    protected UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }

}
