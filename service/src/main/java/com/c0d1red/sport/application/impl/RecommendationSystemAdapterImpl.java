package com.c0d1red.sport.application.impl;

import com.c0d1red.RecommendationFacade;
import com.c0d1red.domain.ResultingRecommendationObject;
import com.c0d1red.domain.SourceRecommendationObject;
import com.c0d1red.sport.application.api.RecommendationSystemAdapter;
import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.article.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecommendationSystemAdapterImpl implements RecommendationSystemAdapter {
    private final RecommendationFacade recommendationFacade = new RecommendationFacade();

    @Override
    public List<Article> createRecommendation(List<Article> allArticles, List<Article> likedArticles) {
        List<SourceRecommendationObject> allSourceObjects = allArticles.stream()
                .map(this::createSourceRecommendationObjectFrom)
                .collect(Collectors.toList());

        List<SourceRecommendationObject> likedSourceObjects = likedArticles.stream()
                .map(this::createSourceRecommendationObjectFrom)
                .collect(Collectors.toList());

        List<ResultingRecommendationObject> recommendations =
                recommendationFacade.createRecommendation(allSourceObjects, likedSourceObjects);

        return recommendations.stream()
                .map(recommendation -> getArticleByIdFromList(recommendation.getSourceId(), allArticles))
                .collect(Collectors.toList());
    }

    private SourceRecommendationObject createSourceRecommendationObjectFrom(Article article) {
        long articleId = article.getId();
        List<Tag> articleTags = article.getTags();
        List<String> articleKeywords = articleTags.stream()
                .map(Tag::getWord)
                .collect(Collectors.toList());
        return new SourceRecommendationObject(articleId, articleKeywords);
    }

    private Article getArticleByIdFromList(long sourceId, List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getId() == sourceId)
                .findFirst()
                .orElse(null);
    }
}
