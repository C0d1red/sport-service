package com.c0d1red.sport.application.api;

import com.c0d1red.sport.application.impl.ArticleChangeRequest;
import com.c0d1red.sport.application.impl.ArticleCreateRequest;
import com.c0d1red.sport.domain.article.Article;

import java.util.Set;

public interface ArticleService {
    Article createNewArticle(ArticleCreateRequest articleCreateRequest);

    Article getArticleById(long articleId);

    Article changeArticle(long articleId, ArticleChangeRequest articleChangeRequest);

    void deleteArticleById(long articleId);

    Article likeArticleById(long articleId);

    Set<Article> getAllForUser();
}
