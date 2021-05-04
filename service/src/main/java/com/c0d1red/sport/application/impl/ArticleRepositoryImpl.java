package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.adapter.jpa.ArticleJpaRepository;
import com.c0d1red.sport.application.api.ArticleNotFoundException;
import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.article.ArticleRepository;
import com.c0d1red.sport.domain.article.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Article> getAllArticles() {
        return articleJpaRepository.findAll();
    }

    @Override
    public List<Article> getArticlesByTag(Tag tag) {
        return articleJpaRepository.findAllByTagsContains(tag);
    }

    @Override
    public void deleteArticleById(long id) {
        articleJpaRepository.deleteById(id);
    }
}
