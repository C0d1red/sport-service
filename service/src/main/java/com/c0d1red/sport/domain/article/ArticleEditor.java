package com.c0d1red.sport.domain.article;

import com.c0d1red.sport.application.impl.ArticleChangeRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleEditor {

    public static void edit(Article article, ArticleChangeRequest articleChangeRequest) {
        changeArticleName(article, articleChangeRequest.getRawName());
        changeArticleText(article, articleChangeRequest.getRawText());
        changeArticleTags(article, articleChangeRequest.getRawTags());
    }

    private static void changeArticleName(Article article, String rawName) {
        article.getName().setName(rawName);
    }

    private static void changeArticleText(Article article, String rawText) {
        article.getText().setText(rawText);
    }

    private static void changeArticleTags(Article article, List<String> rawTags) {
        List<Tag> newTags = rawTags.stream()
                .map(Tag::from).collect(Collectors.toList());
        article.setTags(newTags);
    }
}
