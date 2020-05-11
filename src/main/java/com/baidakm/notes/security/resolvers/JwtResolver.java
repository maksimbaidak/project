package com.baidakm.notes.security.resolvers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.baidakm.notes.security.providers.jwt.JwtAuth;

final class JwtResolver implements AuthResolver {

	@Override
	public Authentication getAuth(HttpServletRequest request) throws AuthenticationException {
		String jwt;
		String authHeader = request.getHeader("Authorization");
		String ipAddress = request.getRemoteAddr();
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwt = authHeader.substring(7);
		} else {
			throw new AuthenticationCredentialsNotFoundException("jwt token not found");
		}
		return new JwtAuth(jwt, ipAddress);
	}

}
