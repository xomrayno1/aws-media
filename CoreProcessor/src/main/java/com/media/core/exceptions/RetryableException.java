package com.media.core.exceptions;

public class RetryableException extends RuntimeException {
	
	public RetryableException(String message) {
		super(message);
	}

}
