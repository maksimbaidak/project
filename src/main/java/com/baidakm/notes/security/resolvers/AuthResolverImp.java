package com.baidakm.notes.security.resolvers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthResolverImp implements AuthResolver {

	@Override
	public Authentication getAuth(HttpServletRequest request) throws AuthenticationException {
		AuthResolver resolver = ResolverFactory.get(request);
		return resolver.getAuth(request);
	}

}
