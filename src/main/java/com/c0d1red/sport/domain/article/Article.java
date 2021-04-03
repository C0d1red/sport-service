package com.c0d1red.sport.domain.article;

import com.c0d1red.sport.domain.supertype.AggregateRoot;
import com.c0d1red.sport.domain.supertype.IdentifiedDomainObject;
import com.c0d1red.sport.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends IdentifiedDomainObject implements AggregateRoot {
    @Embedded
    private ArticleName name;
    @Embedded
    private ArticleText text;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "KEYWORD", joinColumns = @JoinColumn(name = "ARTICLE_ID"))
    private List<Keyword> keywords;
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private User author;
    private LocalDate createdDate;
}
