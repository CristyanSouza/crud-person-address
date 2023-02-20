package com.br.attornatus.exception;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public PersonNotFoundException(String message) {
		this.message = message;
	}
	
	public PersonNotFoundException() {}

	public String getMessage() {
		return message;
	}
	
	

}
