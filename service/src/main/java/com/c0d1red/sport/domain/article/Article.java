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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    @CollectionTable(name = "TAG", joinColumns = @JoinColumn(name = "ARTICLE_ID"))
    private List<Tag> tags;
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private User author;
    private LocalDate createdDate;
    @ManyToMany
    @JoinTable(name = "liker_article",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "liker_id"))
    private Set<User> likers;

    public Article(ArticleName name, ArticleText text, List<Tag> tags, User author, LocalDate createdDate) {
        this.name = name;
        this.text = text;
        this.tags = tags;
        this.author = author;
        this.createdDate = createdDate;
        likers = new HashSet<>();
    }

    public boolean isLikedBy(User liker) {
        return likers.contains(liker);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Article article = (Article) o;
        return Objects.equals(name, article.name) &&
                Objects.equals(text, article.text) &&
                Objects.equals(tags, article.tags) &&
                Objects.equals(author, article.author) &&
                Objects.equals(createdDate, article.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, text, tags, author, createdDate);
    }
}
