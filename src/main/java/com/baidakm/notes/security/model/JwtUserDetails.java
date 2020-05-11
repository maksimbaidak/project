package com.baidakm.notes.security.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

	private static final long serialVersionUID = -4412579960527725550L;
	
	private	com.baidakm.notes.storage.domain.User user;

	public JwtUserDetails(String username, String password) {
		super(username, password, (Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());
	}

	public JwtUserDetails(com.baidakm.notes.storage.domain.User user) {
		super(user.getNick(), user.getPass(), (Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());
		this.user = user;
	}

	public Object getPrincipal() {
		//TODO access to useful auth info
		return user;
	}
}
