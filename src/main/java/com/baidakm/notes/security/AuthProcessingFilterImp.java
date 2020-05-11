package com.baidakm.notes.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.baidakm.notes.security.resolvers.AuthResolver;
import com.baidakm.notes.storage.domain.User;

public class AuthProcessingFilterImp extends AbstractAuthenticationProcessingFilter {
	
	private AuthResolver resolver;
	
	private AuthenticationFailureHandler failHandle;

	public AuthProcessingFilterImp(RequestMatcher requiresAuthenticationRequestMatcher,
		AuthResolver resolver, AuthenticationFailureHandler handler) {
		super(requiresAuthenticationRequestMatcher);
		this.resolver = resolver;
		this.failHandle = handler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		return getAuthenticationManager()
				.authenticate(resolver.getAuth(request));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		//TODO unsuccess auth handler
		SecurityContextHolder.clearContext();
		failHandle.onAuthenticationFailure(request, response, failed);
	}
}
