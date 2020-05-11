package com.baidakm.notes.service.exceptions;

public class UserServiceException extends RuntimeException {

	public UserServiceException(String msg) {
		super(msg);
	}
	
	public UserServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
