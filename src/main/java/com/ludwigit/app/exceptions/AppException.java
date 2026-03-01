package com.ludwigit.app.exceptions;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class AppException extends Exception {

	public final int code;
	public final LocalDateTime timestamp;

	public AppException(
		String message,
		HttpStatusCode code
	) {
		super(message);
		this.code = code.value();
		this.timestamp = LocalDateTime.now();
	}

}
