package com.c0d1red.sport.application.impl;

import lombok.Value;

@Value
public class UserCreateRequest {
    String rawUsername;
    String rawPassword;
}
