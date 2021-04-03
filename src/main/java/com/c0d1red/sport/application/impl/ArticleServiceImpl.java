package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.application.api.ArticleService;
import com.c0d1red.sport.application.api.SecurityService;
import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.article.ArticleEditor;
import com.c0d1red.sport.domain.article.ArticleFactory;
import com.c0d1red.sport.domain.article.ArticleRepository;
import com.c0d1red.sport.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleFactory articleFactory;
    private final ArticleRepository articleRepository;
    private final SecurityService securityService;

    @Override
    public Article createNewArticle(ArticleCreateRequest articleCreateRequest) {
        User author = securityService.getAuthenticatedUser();
        Article createdArticle = articleFactory.createFrom(articleCreateRequest, author);
        return articleRepository.save(createdArticle);
    }

    @Override
    public Article getArticleById(long articleId) {
        return articleRepository.getArticleById(articleId);
    }

    @Override
    public Article changeArticle(long articleId, ArticleChangeRequest articleChangeRequest) {
        Article article = articleRepository.getArticleById(articleId);
        ArticleEditor.edit(article, articleChangeRequest);
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticleById(long articleId) {
        articleRepository.deleteArticleById(articleId);
    }
}
