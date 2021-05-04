package com.c0d1red.sport.domain.article;

import java.util.List;

public interface ArticleRepository {
    Article save(Article article);

    Article getArticleById(long id);

    List<Article> getAllArticles();

    List<Article> getArticlesByTag(Tag tag);

    void deleteArticleById(long id);
}
