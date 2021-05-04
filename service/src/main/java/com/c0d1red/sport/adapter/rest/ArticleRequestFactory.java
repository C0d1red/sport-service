package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.application.impl.ArticleChangeRequest;
import com.c0d1red.sport.application.impl.ArticleCreateRequest;

public class ArticleRequestFactory {

    public static ArticleCreateRequest createCreateRequestFrom(ArticleDto articleDto) {
        return new ArticleCreateRequest(
                articleDto.getName(),
                articleDto.getText(),
                articleDto.getTags()
        );
    }

    public static ArticleChangeRequest createChangeRequestFrom(ArticleDto articleDto) {
        return new ArticleChangeRequest(
                articleDto.getName(),
                articleDto.getText(),
                articleDto.getTags()
        );
    }
}
