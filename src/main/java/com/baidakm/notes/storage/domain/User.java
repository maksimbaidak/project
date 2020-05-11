package com.baidakm.notes.storage.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.SqlResultSetMapping;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;

@NamedNativeQuery(
	    name = "inspect_user",
	    query = "select * from inspect_user(?)",
	    resultSetMapping = "user_fields"
	)
@SqlResultSetMapping(
		name = "user_fields",
	    entities = {
	        @EntityResult(
	            entityClass = User.class,
	            fields = {
	                @FieldResult(
	                    name = "user_id",
	                    column = "usr_id"
	                ),
	                @FieldResult(
	                    name = "nick",
	                    column = "nck"
	                ),
	                @FieldResult(
	                    name = "pass",
	                    column = "pwd"
	                )
	            }
	        )
	    }
	)

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int user_id;
	private String nick;
	private String pass;
	
	@OneToMany(mappedBy="user")
	@Fetch(FetchMode.JOIN)
	@JsonManagedReference
	protected List<Note> noteList = new ArrayList<Note>();
	
	public User() {}
	
	public User(String nick, String pass) {
		this.nick = nick;
		this.pass = pass;
	}
	
	public void addNote(Note note) {
		this.noteList.add(note);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/**
	 * Calculates the summ of nick codepoints
	 * @param nick
	 * @return
	 */
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public List<Note> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<Note> noteList) {
		this.noteList = noteList;
	}

	@Override
	public String toString() {
		return "{" + user_id + ", "+ nick + "}" ;
	}
}
