package com.kedacom.xlite.security.context;

import com.kedacom.xlite.meeting.bean.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * security 用户上下文
 */
public class UserContext {

	public static User getCurrentUser() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication && !(authentication instanceof AnonymousAuthenticationToken)) {
			user = (User) authentication.getPrincipal();
		}
		return user;
	}

	public static void setCurrentUser(final User user) {
		Authentication authentication = new AbstractAuthenticationToken(null) {

			private static final long serialVersionUID = 446590017585142422L;

			@Override
			public Object getPrincipal() {
				return user;
			}

			@Override
			public Object getCredentials() {
				return user;
			}
		};
		// 不管用户上下文之前的信息，直接设置当前用户信息
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public static List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication && !(authentication instanceof AnonymousAuthenticationToken)) {
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				auths.add(authority);
			}
		}
		return auths;
	}


	public static String getCurrentUserMoid() {
		User user = getCurrentUser();
		return user == null ? null : user.getMoid();
	}

	public static String getCurrentUserName() {
		User user = getCurrentUser();
		return user == null ? null : user.getName();
	}

	public static String getCurrentUserEmail() {
		User user = getCurrentUser();
		return user == null ? null : user.getEmail();
	}

	public static String getCurrentUserE164() {
		User user = getCurrentUser();
		return user == null ? null : user.getE164();
	}

	public static String getCurrentUserCompanyMoid() {
		User user = getCurrentUser();
		return user == null ? null : user.getCompanyMoid();
	}


	public static Locale getCurrentLocale() {
		return Locale.CHINA;
	}

	public static Locale getLocale() {
		return Locale.CHINA;
	}
}
