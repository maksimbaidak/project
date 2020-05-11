package com.baidakm.notes.security;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baidakm.notes.security.model.JwtUserDetails;
import com.baidakm.notes.storage.dao.UserRepo;
import com.baidakm.notes.storage.domain.User;

@Service
public class UsrDetailsService implements UserDetailsService {

	@Autowired private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
		throws UsernameNotFoundException {
			User user;
			try {	user = repo.inspectUser(username);
			}catch (NoResultException e) {
					throw new UsernameNotFoundException("user not found", e);
			}
			return new JwtUserDetails(user);
	}

}
