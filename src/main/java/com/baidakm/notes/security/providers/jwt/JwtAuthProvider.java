package com.baidakm.notes.security.providers.jwt;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.baidakm.notes.security.UsrDetailsService;
import com.baidakm.notes.security.model.JwtUserDetails;

import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthProvider implements AuthenticationProvider {
	
	@Autowired private JwtUtil jwtUtil;
	
	@Autowired private UsrDetailsService detailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authenticate((JwtAuth)authentication);
	}
	
	private Authentication authenticate(JwtAuth auth) throws AuthenticationException {
		
		Map<String, Object> claims;
		UserDetails details;
		
		try{	claims = jwtUtil.extractToken((String) auth.getCredentials());
		}catch (SignatureException e) {
				throw new BadCredentialsException("invalid token", e);
		}
		
		details = detailsService.loadUserByUsername((String) claims.get("name"));
		
		if(details.getUsername().equals((String) claims.get("name")) &
			details.getPassword().equals((String) claims.get("pass"))) {
			
				if(auth.getDetails().toString().equals((String) claims.get("ip"))) {
					
					auth.setAuthenticated(true);
					
				}else {throw new InsufficientAuthenticationException("not familiar address");}
				
		}else {throw new BadCredentialsException("username or pass is invalid");}
		
		return new JwtAuth((JwtUserDetails) details);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuth.class.equals(authentication);
	}
}
