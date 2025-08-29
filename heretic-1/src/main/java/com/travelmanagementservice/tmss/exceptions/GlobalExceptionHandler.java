package com.travelmanagementservice.tmss.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.travelmanagementservice.tmss.entity.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse<>(false,ex.getMessage(),null));
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ApiResponse<Void>> handleInvalid(InvalidRequestException ex){
		return ResponseEntity.badRequest()
				.body(new ApiResponse<> (false,ex.getMessage(),null));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ApiResponse<>(false, "An unexpected error occurred", null));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleUserNotFound(UserNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse<> (false,ex.getMessage(),null));
	}

}
