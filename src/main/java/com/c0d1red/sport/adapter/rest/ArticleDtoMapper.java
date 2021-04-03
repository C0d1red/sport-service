package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.article.Keyword;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleDtoMapper {
    private final ModelMapper modelMapper;

    public ArticleDtoMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper.typeMap(Article.class, ArticleDto.class)
                .addMapping(article -> article.getName().getName(), ArticleDto::setName)
                .addMapping(article -> article.getText().getText(), ArticleDto::setText)
                .addMapping(article -> article.getAuthor().getUsername().getName(), ArticleDto::setAuthorUsername)
                .setPostConverter(articleDtoPostConverter());
    }

    public ArticleDto mapDtoFrom(Article article) {
        return modelMapper.map(article, ArticleDto.class);
    }

    public Converter<Article, ArticleDto> articleDtoPostConverter() {
        return context -> {
            Article source = context.getSource();
            ArticleDto destination = context.getDestination();

            List<String> rawKeyWords = source.getKeywords().stream()
                    .map(Keyword::getWord).collect(Collectors.toList());
            destination.setKeyWords(rawKeyWords);
            return destination;
        };
    }
}
