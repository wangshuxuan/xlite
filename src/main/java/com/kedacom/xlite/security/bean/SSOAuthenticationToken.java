package com.kedacom.xlite.security.bean;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SSOAuthenticationToken extends AbstractAuthenticationToken {

	/** serialVersionUID */
	private static final long serialVersionUID = 8599905677797446396L;

	private final Object principal;

	private final int keyHash;

	public SSOAuthenticationToken(String key, Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);

		if ((key == null) || ("".equals(key)) || (principal == null) || "".equals(principal)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}

		this.keyHash = key.hashCode();
		this.principal = principal;
		setAuthenticated(true);
	}

	public Object getCredentials() {
		return "";
	}

	public int getKeyHash() {
		return this.keyHash;
	}

	public Object getPrincipal() {
		return this.principal;
	}

	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}

		if (obj instanceof SSOAuthenticationToken) {
			SSOAuthenticationToken test = (SSOAuthenticationToken) obj;

			if (this.getKeyHash() != test.getKeyHash()) {
				return false;
			}

			return true;
		}

		return false;
	}

}
