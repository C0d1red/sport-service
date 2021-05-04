package com.c0d1red;

import com.c0d1red.domain.ResultingRecommendationObject;
import com.c0d1red.domain.SourceRecommendationObject;
import com.c0d1red.engine.KeywordRatingCalculator;
import com.c0d1red.engine.ObjectRatingCalculator;
import com.c0d1red.engine.RecommendationSorter;

import java.util.List;
import java.util.Map;

public class RecommendationFacade {
    private final KeywordRatingCalculator keywordRatingCalculator;
    private final ObjectRatingCalculator objectRatingCalculator;
    private final RecommendationSorter recommendationSorter;

    public RecommendationFacade() {
        keywordRatingCalculator = new KeywordRatingCalculator();
        objectRatingCalculator = new ObjectRatingCalculator();
        recommendationSorter = new RecommendationSorter();
    }

    public List<ResultingRecommendationObject> createRecommendation(List<SourceRecommendationObject> allObjects,
                                                                    List<SourceRecommendationObject> likedObjects) {
        Map<String, Integer> keywordRating = keywordRatingCalculator.calculateKeywordRating(likedObjects);
        Map<ResultingRecommendationObject, Float> objectRating = objectRatingCalculator.calculateRating(allObjects, keywordRating);
        return recommendationSorter.sortRecommendations(objectRating);
    }

}
