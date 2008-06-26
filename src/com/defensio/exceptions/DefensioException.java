package com.defensio.exceptions;

public class DefensioException extends Exception {

	public DefensioException() {
	}

	public DefensioException(String message, Throwable cause) {
		super(message, cause);
	}

	public DefensioException(String message) {
		super(message);
	}

	public DefensioException(Throwable cause) {
		super(cause);
	}	
}
