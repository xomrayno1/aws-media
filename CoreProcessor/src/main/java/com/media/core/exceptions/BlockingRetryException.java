package com.media.core.exceptions;

public class BlockingRetryException extends RuntimeException{
	
	public BlockingRetryException(String message) {
		super(message);
	}
}
