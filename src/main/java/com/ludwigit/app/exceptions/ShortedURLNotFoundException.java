package com.ludwigit.app.exceptions;

import org.springframework.http.HttpStatus;

public class ShortedURLNotFoundException extends AppException {

	public ShortedURLNotFoundException() {
		super("Shorted URL not found", HttpStatus.NOT_FOUND);
	}

	public ShortedURLNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

}
