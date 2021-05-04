package com.c0d1red.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SourceRecommendationObject extends RecommendationObject {
    private List<String> keyWords;

    public SourceRecommendationObject(long sourceId, List<String> keyWords) {
        super(sourceId);
        this.keyWords = keyWords;
    }
}
