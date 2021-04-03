package com.c0d1red.sport.domain.article;

import com.c0d1red.sport.application.impl.ArticleChangeRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleEditor {

    public static void edit(Article article, ArticleChangeRequest articleChangeRequest) {
        changeArticleName(article, articleChangeRequest.getRawName());
        changeArticleText(article, articleChangeRequest.getRawText());
        changeArticleKeyWords(article, articleChangeRequest.getRawKeyWords());
    }

    private static void changeArticleName(Article article, String rawName) {
        article.getName().setName(rawName);
    }

    private static void changeArticleText(Article article, String rawText) {
        article.getText().setText(rawText);
    }

    private static void changeArticleKeyWords(Article article, List<String> rawKeyWords) {
        List<Keyword> newKeywords = rawKeyWords.stream()
                .map(Keyword::from).collect(Collectors.toList());
        article.setKeywords(newKeywords);
    }
}
