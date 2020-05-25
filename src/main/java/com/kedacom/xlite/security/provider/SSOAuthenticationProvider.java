package com.kedacom.xlite.security.provider;

import com.kedacom.xlite.security.bean.SSOAuthenticationToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.util.Assert;

public class SSOAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {

	// ~ Instance fields
	// ================================================================================================

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private static final String key = "kedacom-ssoKey-GVVxVQMjhNcccKYviQk7MocaWQnoJVz785KG";

	// ~ Methods
	// =======================================================================================================

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		if (key.hashCode() != ((SSOAuthenticationToken) authentication).getKeyHash()) {
			throw new BadCredentialsException(
					messages.getMessage("SSOAuthenticationProvider.incorrectKey", "The presented SSOAuthenticationToken does not contain the expected key"));
		}

		return authentication;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (SSOAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.messages, "A message source must be set");
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

}
