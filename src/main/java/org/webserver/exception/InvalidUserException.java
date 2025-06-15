package org.webserver.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(String.format("Invalid user: %s", message));
    }
}


