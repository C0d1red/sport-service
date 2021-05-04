package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.application.api.SecurityService;
import com.c0d1red.sport.application.api.UserService;
import com.c0d1red.sport.domain.user.User;
import com.c0d1red.sport.domain.user.UserFactory;
import com.c0d1red.sport.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final SecurityService securityService;

    @Override
    public String createNewUser(UserCreateRequest userCreateRequest) {
        User user = userFactory.createFrom(userCreateRequest);
        userRepository.save(user);
        return securityService.authenticateAndCreateToken(userCreateRequest.getRawUsername(), userCreateRequest.getRawPassword());
    }
}
