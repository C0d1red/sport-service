package com.c0d1red.sport.domain.user;

import com.c0d1red.sport.domain.supertype.ValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Username implements ValueObject {
    private String name;

    public static Username from(String name) {
        return new Username(name);
    }
}
