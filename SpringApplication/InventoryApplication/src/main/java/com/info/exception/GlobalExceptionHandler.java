package com.info.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	 

	    @ExceptionHandler(UnauthorizedException.class)
	    public ResponseEntity<String> unauthorizedHandler(UnauthorizedException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	    }
	    

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleInternalServerError(Exception e) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Something went wrong !! try again");
	    }
	    
	    
	    @ExceptionHandler(InsufficientStockException.class)
	    public ResponseEntity<String> insufficientStockException(InsufficientStockException e){
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    	
	    }
	    
	    @ExceptionHandler(InvalidResourceException.class)
	    public ResponseEntity<String> invalidResourceException(InvalidResourceException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    	
	    }
	    
	    @ExceptionHandler(EmailAlreadyExistsException.class)
	    public ResponseEntity<String> emailAlreadyExistsException(EmailAlreadyExistsException e){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    	
	    	
	    }
}
