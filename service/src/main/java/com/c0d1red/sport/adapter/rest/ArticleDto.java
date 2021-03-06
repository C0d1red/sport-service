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
    private Long id;
    private String name;
    private String text;
    private List<String> tags;
    private String authorUsername;
    private LocalDate createdDate;
    private Integer likersNum;
    private List<ArticleStatusDto> statuses;
}
