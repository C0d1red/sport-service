package com.c0d1red.engine;

import com.c0d1red.domain.SourceRecommendationObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordRatingCalculator {
    private static final int LIKE_POINTS = 3;

    public Map<String, Integer> calculateKeywordRating(List<SourceRecommendationObject> likedObjects) {
        Map<String, Integer> keywordRating = new HashMap<>();
        for (SourceRecommendationObject likedObject : likedObjects) {
            List<String> keyWords = likedObject.getKeyWords();
            for (String keyWord : keyWords) {
                if (keywordRating.containsKey(keyWord)) {
                    int currentRate = keywordRating.get(keyWord);
                    keywordRating.put(keyWord, currentRate + LIKE_POINTS);
                } else {
                    keywordRating.put(keyWord, LIKE_POINTS);
                }
            }
        }
        return keywordRating;
    }
}
