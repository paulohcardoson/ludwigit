package com.ludwigit.app.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidURLException extends AppException {

	public InvalidURLException() {
		super("Invalid URL", HttpStatus.BAD_REQUEST);
	}

	public InvalidURLException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

}
