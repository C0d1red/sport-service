package com.c0d1red.sport.domain.user;

import com.c0d1red.sport.domain.supertype.AggregateRoot;
import com.c0d1red.sport.domain.supertype.IdentifiedDomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "\"user\"")
@Data
@AllArgsConstructor
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

    @AllArgsConstructor
    public enum Role {
        ADMIN, VISITOR
    }
}
