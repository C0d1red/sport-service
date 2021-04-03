package com.c0d1red.sport.adapter.jpa;

import com.c0d1red.sport.domain.user.User;
import com.c0d1red.sport.domain.user.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(Username username);
}
