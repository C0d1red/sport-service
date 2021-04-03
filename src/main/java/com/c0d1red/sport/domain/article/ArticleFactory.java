package com.c0d1red.sport.domain.article;

import com.c0d1red.sport.application.impl.ArticleCreateRequest;
import com.c0d1red.sport.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticleFactory {

    public Article createFrom(ArticleCreateRequest articleCreateRequest, User author) {
        List<Keyword> keywords = createKeyWordsFrom(articleCreateRequest.getRawKeyWords());
        ArticleName articleName = ArticleName.from(articleCreateRequest.getRawName());
        ArticleText articleText = ArticleText.from(articleCreateRequest.getRawText());
        LocalDate creationDate = LocalDate.now();
        return new Article(
                articleName,
                articleText,
                keywords,
                author,
                creationDate
        );
    }

    private List<Keyword> createKeyWordsFrom(List<String> rawKeyWords) {
        return rawKeyWords.stream()
                .map(Keyword::from).collect(Collectors.toList());
    }
}
