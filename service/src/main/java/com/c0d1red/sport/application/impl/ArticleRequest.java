package com.c0d1red.sport.application.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class ArticleRequest {
    private final String rawName;
    private final String rawText;
    private final List<String> rawTags;
}
