package com.baidakm.notes.storage.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import com.baidakm.notes.storage.domain.Note;
import com.baidakm.notes.storage.domain.User;

@Repository
@ComponentScan("com.baidakm.notes.orm.configuration")
public class NoteRepo {

	@Autowired private SessionFactory factory;

	public List<Note> getAll(User user) {
		List<Note> noteList;
		try(Session session = factory.openSession();){
			noteList = session
						.get(User.class, user.getUser_id())
						.getNoteList();
		}
		return noteList;
	}

	public void addNote(User user, Note note) {
		try(Session session = factory.openSession();){
			session.beginTransaction();
			user = session.find(User.class, user.getUser_id());
			note.setUser(user);
			session.persist(note);
			session.getTransaction().commit();
		}
	}
}
