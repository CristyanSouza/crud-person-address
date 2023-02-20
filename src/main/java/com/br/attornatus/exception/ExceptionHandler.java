package com.br.attornatus.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<TemplateMessage> personNotFound(PersonNotFoundException ex){
		
		TemplateMessage error = new TemplateMessage(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<TemplateMessage> validation(MethodArgumentNotValidException ex){
				
		TemplateMessage error = new TemplateMessage(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
