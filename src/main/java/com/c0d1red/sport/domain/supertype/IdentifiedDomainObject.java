package com.c0d1red.sport.domain.supertype;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class IdentifiedDomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
