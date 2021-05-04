package com.c0d1red.sport.adapter.jpa;

import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.article.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByTagsContains(Tag tag);
}
