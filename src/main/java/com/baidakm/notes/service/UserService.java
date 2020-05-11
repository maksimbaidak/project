package com.baidakm.notes.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidakm.notes.service.exceptions.UserServiceException;
import com.baidakm.notes.service.exceptions.UsernameAlreadyExistException;
import com.baidakm.notes.storage.dao.UserRepo;
import com.baidakm.notes.storage.domain.User;

@Service
public class UserService {
	
	@Autowired private UserRepo repo;

	public void add(User user) throws UserServiceException {
		try {
			repo.inspectUser(user.getNick());
			throw new UsernameAlreadyExistException("nick already exist");
		}catch(NoResultException e) {
			repo.save(user);
		}
	}
}
