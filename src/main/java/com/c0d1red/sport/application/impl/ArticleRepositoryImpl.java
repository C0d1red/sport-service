package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.adapter.jpa.ArticleJpaRepository;
import com.c0d1red.sport.application.api.ArticleNotFoundException;
import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    private final ArticleJpaRepository articleJpaRepository;

    @Override
    public Article save(Article article) {
        return articleJpaRepository.save(article);
    }

    @Override
    public Article getArticleById(long id) {
        return articleJpaRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    @Override
    public void deleteArticleById(long id) {
        articleJpaRepository.deleteById(id);
    }
}
