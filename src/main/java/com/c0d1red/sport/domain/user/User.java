package com.c0d1red.sport.domain.user;

import com.c0d1red.sport.domain.article.Article;
import com.c0d1red.sport.domain.supertype.AggregateRoot;
import com.c0d1red.sport.domain.supertype.IdentifiedDomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
public class User extends IdentifiedDomainObject implements AggregateRoot {
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "USERNAME"))
    private Username username;
    @Embedded
    @AttributeOverride(name = "hash", column = @Column(name = "PASSWORD"))
    private Password password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "likers")
    private Set<Article> likedArticles;

    public User(Username username, Password password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @AllArgsConstructor
    public enum Role {
        COMMON, ADMIN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, role);
    }
}
