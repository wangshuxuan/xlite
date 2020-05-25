package com.kedacom.xlite.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsService
 * @author wangshuxuan
 * @date 2020/3/30 16:22
 */
public interface UserDetailsService {

    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
