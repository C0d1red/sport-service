package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.application.impl.UserCreateRequest;
import com.c0d1red.sport.domain.user.User.Role;

public class UserRequestFactory {

    public static UserCreateRequest createFrom(CredentialsDto credentialsDto) {
        return new UserCreateRequest(credentialsDto.getUsername(), credentialsDto.getPassword(), Role.valueOf(credentialsDto.getRole()));
    }
}
