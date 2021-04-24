package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.application.api.ArticleService;
import com.c0d1red.sport.application.impl.ArticleChangeRequest;
import com.c0d1red.sport.application.impl.ArticleCreateRequest;
import com.c0d1red.sport.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return articleMapper.mapDtoFrom(article);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ArticleDto update(@PathVariable long id, ArticleDto articleDto) {
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
        Article likedArticle = articleService.likeArticleById(id);
        return articleMapper.mapDtoFrom(likedArticle);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    private CollectionDto<ArticleDto> getAllForUser() {
        Set<Article> likedArticle = articleService.getAllForUser();
        return new CollectionDto<>(likedArticle.stream()
                .map(articleMapper::mapDtoFrom)
                .collect(Collectors.toSet()));
    }
}
