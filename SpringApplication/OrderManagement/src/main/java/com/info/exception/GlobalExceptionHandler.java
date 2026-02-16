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
	    
	    
	    @ExceptionHandler(CartNotFoundException.class)
	    public ResponseEntity<String> cartNotFoundException(CartNotFoundException e){
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
     
	    
	    @ExceptionHandler(StockNotAvailableException.class)
	    public ResponseEntity<String> stockNotFoundException(StockNotAvailableException e){
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    	
	    }
	    
	    
	    @ExceptionHandler(DuplicateOrderException.class)
	    public ResponseEntity<String> duplicateOrderException(DuplicateOrderException e){
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
}
