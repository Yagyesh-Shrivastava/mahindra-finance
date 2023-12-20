package com.app.exception;

@SuppressWarnings("serial")
public class LeadNotFoundException extends RuntimeException {
    public LeadNotFoundException(String message) {
        super(message);
    }
}
