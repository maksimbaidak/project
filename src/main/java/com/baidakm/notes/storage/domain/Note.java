package com.baidakm.notes.storage.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="notes")
public class Note {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int note_id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	protected User user;
	
	private String contents;

	public Note() {}

	public int getNote_id() {
		return note_id;
	}

	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return contents;
	}
}
