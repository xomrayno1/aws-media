package com.media.core.exceptions;

public class RedisException extends RuntimeException{
	
	public RedisException() {
        super();
    }

    public RedisException(String message) {
        super(message);
    }

}
