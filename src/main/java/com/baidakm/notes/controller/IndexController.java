package com.baidakm.notes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baidakm.notes.security.model.AuthenticationRequest;
import com.baidakm.notes.security.model.AuthenticationResponse;
import com.baidakm.notes.security.model.JwtUserDetails;
import com.baidakm.notes.security.providers.jwt.JwtUtil;
import com.baidakm.notes.service.UserService;
import com.baidakm.notes.service.exceptions.UsernameAlreadyExistException;
import com.baidakm.notes.storage.domain.User;

@RestController
@RequestMapping("/")
public class IndexController {
	
	@Autowired private UserService userService;
	
	@Autowired private JwtUtil jwtUtil;
	
	@GetMapping
	public void get() {}
	
	@PostMapping
	@RequestMapping("signin")
	public void signin() {}
	
	@PostMapping
	@RequestMapping("signup")
	public ResponseEntity<AuthenticationResponse> 
		signup(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request) {
		User user = new User(	authenticationRequest.getUsername(),
								authenticationRequest.getPassword());
		try {	userService.add(user);
				String token = jwtUtil.generateToken(	new JwtUserDetails(user), 
														request.getRemoteAddr());
				return ResponseEntity.ok(new AuthenticationResponse(token));
		}catch (UsernameAlreadyExistException e) {
			// TODO handle nick already exist exception
			return null;
		}
	}
	
	@PostMapping
	@RequestMapping("refresh")
	public ResponseEntity<AuthenticationResponse>
		refreshToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request){
		//TODO refresh token
		// stub method
		User user = new User(	authenticationRequest.getUsername(),
								authenticationRequest.getPassword());
		String token = jwtUtil.generateToken(	new JwtUserDetails(user), 
												request.getRemoteAddr());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
}
