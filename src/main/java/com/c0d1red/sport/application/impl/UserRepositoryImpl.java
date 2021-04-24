package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.adapter.jpa.UserJpaRepository;
import com.c0d1red.sport.application.api.UserNotFoundException;
import com.c0d1red.sport.domain.user.User;
import com.c0d1red.sport.domain.user.UserRepository;
import com.c0d1red.sport.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public User getUserByUsername(Username username) {
        return userJpaRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with username %s not found", username.getName())));
    }

    @Override
    public User getUserById(long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));
    }
}
