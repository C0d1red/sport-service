package com.c0d1red.sport.application.impl;

import java.util.List;

public class ArticleChangeRequest extends ArticleRequest {
    public ArticleChangeRequest(String rawName, String rawText, List<String> rawTags) {
        super(rawName, rawText, rawTags);
    }
}
