package com.c0d1red.sport.application.api;

import com.c0d1red.sport.application.impl.ArticleChangeRequest;
import com.c0d1red.sport.application.impl.ArticleCreateRequest;
import com.c0d1red.sport.domain.article.Article;

public interface ArticleService {
    Article createNewArticle(ArticleCreateRequest articleCreateRequest);

    Article getArticleById(long articleId);

    Article changeArticle(long articleId, ArticleChangeRequest articleChangeRequest);

    void deleteArticleById(long articleId);
}
