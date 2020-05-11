package com.baidakm.notes.storage.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.baidakm.notes.storage.domain.User;

@Repository
@ComponentScan("com.baidakm.notes.orm.configuration")
public class UserRepo {

	@Autowired private SessionFactory factory;
	
	public User inspectUser(String nick) {
		User user;
		try(Session session = factory.openSession();){
			user = (User) session.createNamedQuery("inspect_user")
								.setParameter(1, nick)
								.getSingleResult();
		}
		return user;
	}
	
	public void save(User user) {
		try(Session session = factory.openSession();){
			session.save(user);
		}
	}
}