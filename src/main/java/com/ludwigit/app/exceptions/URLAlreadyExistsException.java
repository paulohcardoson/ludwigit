package com.ludwigit.app.exceptions;

import org.springframework.http.HttpStatus;

public class URLAlreadyExistsException extends AppException {

	public URLAlreadyExistsException() {
		super("URL already exists", HttpStatus.UNAUTHORIZED);
	}

	public URLAlreadyExistsException(String message) {
		super(message, HttpStatus.UNAUTHORIZED);
	}

}
