package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LeadExistsException extends RuntimeException {
    public LeadExistsException(String message) {
        super(message);
    }
}
