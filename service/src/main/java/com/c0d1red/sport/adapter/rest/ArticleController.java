package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.application.api.ArticleService;
import com.c0d1red.sport.application.impl.ArticleChangeRequest;
import com.c0d1red.sport.application.impl.ArticleCreateRequest;
import com.c0d1red.sport.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleDtoMapper articleMapper;
    private final ArticleService articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ArticleDto createNew(@RequestBody ArticleDto articleDto) {
        ArticleCreateRequest articleCreateRequest = ArticleRequestFactory.createCreateRequestFrom(articleDto);
        Article createdArticle = articleService.createNewArticle(articleCreateRequest);
        return articleMapper.mapDtoFrom(createdArticle);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ArticleDto get(@PathVariable long id) {
        Article article = articleService.getArticleById(id);
        boolean likeStatus = articleService.getArticleLikeStatusForUser(id);
        boolean accessStatus = articleService.getArticleAccessStatusForUser(id);
        return articleMapper.mapDtoWithStatusesFrom(article, likeStatus, accessStatus);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ArticleDto update(@PathVariable long id, @RequestBody ArticleDto articleDto) {
        ArticleChangeRequest articleChangeRequest = ArticleRequestFactory.createChangeRequestFrom(articleDto);
        Article updatedArticle = articleService.changeArticle(id, articleChangeRequest);
        return articleMapper.mapDtoFrom(updatedArticle);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void delete(@PathVariable long id) {
        articleService.deleteArticleById(id);
    }

    @PutMapping("/like/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ArticleDto likeArticle(@PathVariable long id) {
        Article likedArticle = articleService.switchArticleLikeById(id);
        return articleMapper.mapDtoFrom(likedArticle);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    private CollectionDto<ArticleDto> getAll() {
        List<Article> articles = articleService.getAllArticles();
        return new CollectionDto<>(articles.stream()
                .map(articleMapper::mapDtoFrom)
                .collect(Collectors.toSet()));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private CollectionDto<ArticleDto> getByTag(@RequestParam String tag) {
        List<Article> articles = articleService.getArticlesByTag(tag);
        return new CollectionDto<>(articles.stream()
                .map(articleMapper::mapDtoFrom)
                .collect(Collectors.toSet()));
    }

    @GetMapping("/me/liked")
    @ResponseStatus(HttpStatus.OK)
    private CollectionDto<ArticleDto> getAllLikedForUser() {
        Set<Article> likedArticle = articleService.getLikedArticlesForUser();
        return new CollectionDto<>(likedArticle.stream()
                .map(articleMapper::mapDtoFrom)
                .collect(Collectors.toSet()));
    }

    @GetMapping("/me/recommended")
    @ResponseStatus(HttpStatus.OK)
    private CollectionDto<ArticleDto> getAllRecommendedForUser() {
        List<Article> recommendedArticles = articleService.getRecommendedArticlesForUser();
        return new CollectionDto<>(recommendedArticles.stream()
                .map(articleMapper::mapDtoFrom)
                .collect(Collectors.toSet()));
    }
}
