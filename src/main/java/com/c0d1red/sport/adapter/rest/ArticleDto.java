package com.c0d1red.sport.adapter.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private long id;
    private String name;
    private String text;
    private List<String> keyWords;
    private String authorUsername;
    private LocalDate createdDate;
}
