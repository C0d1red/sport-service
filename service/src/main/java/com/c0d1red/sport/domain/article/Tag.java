package com.c0d1red.sport.domain.article;

import com.c0d1red.sport.domain.supertype.ValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Data
@Embeddable
@Table(name = "tag")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag implements ValueObject {
    String word;

    public static Tag from(String word) {
        return new Tag(word);
    }
}
