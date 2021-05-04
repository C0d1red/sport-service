package com.c0d1red.sport.application.api;

public class ArticleNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Article with id %s not found";

    public ArticleNotFoundException(long id) {
        super(String.format(MESSAGE, id));
    }
}
