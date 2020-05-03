package com.epsilon.accountapi.exception;

public class DoesNotExistException extends RuntimeException {
    public DoesNotExistException() {
        super();
    }

    public DoesNotExistException(String message) {
        super(message);
    }
}
