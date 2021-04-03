package com.c0d1red.sport.application.api;

import com.c0d1red.sport.application.impl.UserCreateRequest;

public interface UserService {
    String createNewUser(UserCreateRequest userCreateRequest);
}
