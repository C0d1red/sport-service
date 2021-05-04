package com.c0d1red.sport.domain.user;

public interface UserRepository {
    User save(User user);

    User getUserByUsername(Username username);

    User getUserById(long id);
}
