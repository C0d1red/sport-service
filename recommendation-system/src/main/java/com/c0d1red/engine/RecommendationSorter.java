package com.c0d1red.engine;

import com.c0d1red.domain.ResultingRecommendationObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendationSorter {
    public List<ResultingRecommendationObject> sortRecommendations(Map<ResultingRecommendationObject, Float> objectRating) {
        List<ResultingRecommendationObject> recommendedObjects = objectRating.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Collections.reverse(recommendedObjects);
        return recommendedObjects;
    }
}
