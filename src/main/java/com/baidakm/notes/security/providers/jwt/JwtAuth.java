package com.baidakm.notes.security.providers.jwt;


import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.baidakm.notes.security.model.JwtUserDetails;

public class JwtAuth extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 5047681218420569863L;
	
	private JwtUserDetails principal;
	
	private String ipAddress;
	private String token;
	
	public JwtAuth(String token, String ipAddress) {
		super(null);
		super.setAuthenticated(false);
		this.ipAddress = ipAddress;
		this.token = token;
	}
	
	public JwtAuth(JwtUserDetails principal) {
		super(null);
		super.setAuthenticated(true);
		this.principal = principal;
	}

	@Override
	public Object getCredentials() {
		return this.token;
	}

	/**
	 * Return the current request address.It's allow to auth provider check
	 * that user is secure login from a familiar address.
	 */
	@Override
	public Object getDetails() {
		return this.ipAddress;
	}

	/**
	 * Return useful info about authentication
	 * like database id of current user 
	 */
	@Override
	public Object getPrincipal() {
		return principal.getPrincipal();
	}
}
