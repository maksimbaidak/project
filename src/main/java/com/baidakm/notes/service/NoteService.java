package com.baidakm.notes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.baidakm.notes.storage.dao.NoteRepo;
import com.baidakm.notes.storage.domain.Note;
import com.baidakm.notes.storage.domain.User;

@Service
public class NoteService {
	
	@Autowired NoteRepo repo;

	public List<Note> getAll() {
		User user = (User) SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getPrincipal();
		return repo.getAll(user);
	}

	public void addNote(Note note) {
		User user = (User) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		repo.addNote(user, note);
	}
}
