package com.c0d1red.sport.domain.article;

public interface ArticleRepository {
    Article save(Article article);

    Article getArticleById(long id);

    void deleteArticleById(long id);
}
