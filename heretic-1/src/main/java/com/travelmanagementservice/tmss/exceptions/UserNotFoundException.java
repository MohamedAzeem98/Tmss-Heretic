package com.travelmanagementservice.tmss.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}

}
