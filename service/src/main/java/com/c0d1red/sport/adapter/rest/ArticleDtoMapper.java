package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.article.Tag;
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

    public ArticleDto mapDtoWithStatusesFrom(Article article, boolean likeStatus, boolean accessStatus) {
        ArticleDto articleDto = mapDtoFrom(article);
        List<ArticleStatusDto> statuses = createStatusesFrom(likeStatus, accessStatus);
        articleDto.setStatuses(statuses);
        return articleDto;
    }

    private List<ArticleStatusDto> createStatusesFrom(boolean likeStatus, boolean accessStatus) {
        ArticleStatusDto likeArticleStatusDto = new ArticleStatusDto("likeStatus", likeStatus);
        ArticleStatusDto accessArticleStatusDto = new ArticleStatusDto("accessStatus", accessStatus);
        return List.of(likeArticleStatusDto, accessArticleStatusDto);
    }

    public Converter<Article, ArticleDto> articleDtoPostConverter() {
        return context -> {
            Article source = context.getSource();
            ArticleDto destination = context.getDestination();

            List<String> rawTags = source.getTags().stream()
                    .map(Tag::getWord).collect(Collectors.toList());
            int likersNum = source.getLikers().size();

            destination.setTags(rawTags);
            destination.setLikersNum(likersNum);
            return destination;
        };
    }
}
