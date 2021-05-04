package com.c0d1red.sport.domain.article;

import com.c0d1red.sport.domain.supertype.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleName implements Entity {
    private String name;

    public static ArticleName from(String name) {
        return new ArticleName(name);
    }
}
