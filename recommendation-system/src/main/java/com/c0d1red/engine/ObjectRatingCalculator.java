package com.c0d1red.engine;

import com.c0d1red.domain.ResultingRecommendationObject;
import com.c0d1red.domain.SourceRecommendationObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectRatingCalculator {
    public Map<ResultingRecommendationObject, Float> calculateRating(List<SourceRecommendationObject> allObjects, Map<String, Integer> keywordRating) {
        Map<ResultingRecommendationObject, Float> objectRating = new HashMap<>();

        for (SourceRecommendationObject object : allObjects) {
            float rating = 0;

            List<String> objectKeywords = object.getKeyWords();
            for (String objectKeyword : objectKeywords) {
                if (keywordRating.containsKey(objectKeyword)) {
                    rating += keywordRating.get(objectKeyword);
                }
            }
            rating = normalizeObjectRating(rating, object.getKeyWords().size());
            ResultingRecommendationObject resultingRecommendationObject = new ResultingRecommendationObject(object.getSourceId(), rating);
            objectRating.put(resultingRecommendationObject, rating);
        }

        return objectRating;
    }

    private float normalizeObjectRating(float articleRating, float tagsCount) {
        return articleRating / tagsCount;
    }
}
