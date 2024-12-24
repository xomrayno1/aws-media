package com.media.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorisedException extends ResponseStatusException{
	
    public UnauthorisedException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

    public UnauthorisedException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
