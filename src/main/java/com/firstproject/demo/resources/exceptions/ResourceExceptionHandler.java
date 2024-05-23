package com.firstproject.demo.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.firstproject.demo.services.exceptions.ResourceNotFoundException;
import com.firstproject.demo.services.exceptions.SpringDataBaseException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());;
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler(SpringDataBaseException.class)
	public ResponseEntity<StandardError> database(SpringDataBaseException e, HttpServletRequest request) {
		String error = "Database error.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());;
		return ResponseEntity.status(status).body(standardError);
	}
}
