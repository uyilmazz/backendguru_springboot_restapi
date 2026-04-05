package com.example.restapiegitimders1.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ErrorResponse handleNoSuchElementException(NoSuchElementException ex){
		return new ErrorResponse(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getGlobalErrors().forEach(error -> errors.put(error.getObjectName(), error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
	}
}
