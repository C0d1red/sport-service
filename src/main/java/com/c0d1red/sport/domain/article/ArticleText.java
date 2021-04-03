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
public class ArticleText implements Entity {
    private String text;

    public static ArticleText from(String text) {
        return new ArticleText(text);
    }
}
