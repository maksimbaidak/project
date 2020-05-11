package com.baidakm.notes.security.resolvers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthResolver {

	public Authentication getAuth(HttpServletRequest request) throws AuthenticationException;
}
