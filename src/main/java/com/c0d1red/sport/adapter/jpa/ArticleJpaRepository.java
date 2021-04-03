package com.c0d1red.sport.adapter.jpa;

import com.c0d1red.sport.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
}
