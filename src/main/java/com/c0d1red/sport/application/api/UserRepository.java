package com.c0d1red.sport.application.api;

import com.c0d1red.sport.domain.user.User;
import com.c0d1red.sport.domain.user.Username;

public interface UserRepository {
    User save(User user);

    User getUserByUsername(Username username);

    User getUserById(long id);
}
