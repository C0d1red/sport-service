package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.domain.user.User;
import lombok.Value;

@Value
public class UserCreateRequest {
    String rawUsername;
    String rawPassword;
    User.Role role;
}
