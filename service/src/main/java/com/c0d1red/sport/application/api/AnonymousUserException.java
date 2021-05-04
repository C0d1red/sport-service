package com.c0d1red.sport.application.api;

public class AnonymousUserException extends RuntimeException {
    private static final String MESSAGE = "Request from anonymous user";

    public AnonymousUserException() {
        super(MESSAGE);
    }
}
