package com.baidakm.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baidakm.notes.service.NoteService;
import com.baidakm.notes.storage.domain.Note;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired NoteService noteService;
	
	@GetMapping
	public ResponseEntity<List<Note>> getNotesList() {
		return ResponseEntity.ok(noteService.getAll());
	}
	
	@PostMapping
	public void setNewNote(@RequestBody Note note) {
		noteService.addNote(note);
	}
}
