package com.c0d1red.sport.infrastrcture;

public class AuthFailedException extends RuntimeException {

    public AuthFailedException(String message) {
        super(message);
    }

}
