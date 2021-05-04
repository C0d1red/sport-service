package com.c0d1red.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class RecommendationObject {
    private long sourceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationObject that = (RecommendationObject) o;
        return sourceId == that.sourceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceId);
    }
}
