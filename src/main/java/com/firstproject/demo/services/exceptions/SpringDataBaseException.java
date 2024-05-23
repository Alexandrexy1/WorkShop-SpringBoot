package com.firstproject.demo.services.exceptions;

public class SpringDataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SpringDataBaseException(String msg) {
		super(msg);
	}
}
