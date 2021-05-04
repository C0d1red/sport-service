package com.c0d1red.sport.application.impl;

import java.util.List;

public class ArticleCreateRequest extends ArticleRequest {
    public ArticleCreateRequest(String rawName, String rawText, List<String> rawTags) {
        super(rawName, rawText, rawTags);
    }
}
