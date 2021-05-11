package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.application.api.AnonymousUserException;
import com.c0d1red.sport.application.api.ArticleService;
import com.c0d1red.sport.application.api.RecommendationSystemAdapter;
import com.c0d1red.sport.application.api.SecurityService;
import com.c0d1red.sport.domain.article.*;
import com.c0d1red.sport.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleFactory articleFactory;
    private final ArticleRepository articleRepository;
    private final SecurityService securityService;
    private final RecommendationSystemAdapter recommendationSystemAdapter;

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

    @Override
    public Article switchArticleLikeById(long articleId) {
        User liker = securityService.getAuthenticatedUser();
        Article article = articleRepository.getArticleById(articleId);

        if (article.isLikedBy(liker)) {
            article.getLikers().remove(liker);
        } else {
            article.getLikers().add(liker);
        }
        return articleRepository.save(article);
    }

    @Override
    public boolean getArticleLikeStatusForUser(long articleId) {
        try {
            User liker = securityService.getAuthenticatedUser();
            Article article = articleRepository.getArticleById(articleId);
            return article.isLikedBy(liker);
        } catch (AnonymousUserException e) {
            return false;
        }
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.getAllArticles();
    }

    @Override
    public List<Article> getArticlesByTag(String tagName) {
        return articleRepository.getArticlesByTag(Tag.from(tagName));
    }

    @Override
    public Set<Article> getLikedArticlesForUser() {
        User authenticatedUser = securityService.getAuthenticatedUser();
        return authenticatedUser.getLikedArticles();
    }

    @Override
    public List<Article> getRecommendedArticlesForUser() {
        List<Article> allArticles = getAllArticles();
        try {
            List<Article> likedArticles = new ArrayList<>(getLikedArticlesForUser());
            return recommendationSystemAdapter.createRecommendation(allArticles, likedArticles);
        } catch (AnonymousUserException e) {
            return allArticles;
        }
    }

    @Override
    public boolean getArticleAccessStatusForUser(long articleId) {
        try {
            User authenticatedUser = securityService.getAuthenticatedUser();
            Article article = articleRepository.getArticleById(articleId);
            User author = article.getAuthor();
            return author.equals(authenticatedUser);
        } catch (AnonymousUserException e) {
            return false;
        }
    }
}
