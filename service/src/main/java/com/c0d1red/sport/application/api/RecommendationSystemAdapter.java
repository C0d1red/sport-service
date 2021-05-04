package com.c0d1red.sport.application.api;

import com.c0d1red.sport.domain.article.Article;

import java.util.List;

public interface RecommendationSystemAdapter {
    List<Article> createRecommendation(List<Article> allArticles, List<Article> likedArticles);
}
