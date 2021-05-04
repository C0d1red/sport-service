package com.c0d1red.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResultingRecommendationObject extends RecommendationObject {
    private float rating;

    public ResultingRecommendationObject(long sourceId, float rating) {
        super(sourceId);
        this.rating = rating;
    }
}
