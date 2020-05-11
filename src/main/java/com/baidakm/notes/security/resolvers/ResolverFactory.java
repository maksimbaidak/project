package com.baidakm.notes.security.resolvers;

import javax.servlet.http.HttpServletRequest;

class ResolverFactory {

	static AuthResolver get(HttpServletRequest request) {
		//TODO logic according for auth types
		return new JwtResolver();
	}
}
