package com.kedacom.xlite.exception;

import org.springframework.security.core.AuthenticationException;

public class SSOException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2202888419005906960L;

	private String code;

	public SSOException(String message) {
		super(message);
	}

	public SSOException(String code, String message) {
		super(message);
		this.code = code;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
