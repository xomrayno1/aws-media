package com.media.core.exceptions;

public class NotRetryableException extends RuntimeException{
	
	public NotRetryableException(String message) {
		super(message);
	}

}
