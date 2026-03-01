package com.ludwigit.app.exceptions;

import org.springframework.http.HttpStatus;

public class URLAlreadyExistsException extends AppException {

	public URLAlreadyExistsException() {
		super("URL already exists", HttpStatus.BAD_REQUEST);
	}

	public URLAlreadyExistsException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

}
