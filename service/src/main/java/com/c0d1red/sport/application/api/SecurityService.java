package com.c0d1red.sport.application.api;

import com.c0d1red.sport.domain.user.User;

public interface SecurityService {

    String authenticateAndCreateToken(String username, String password);

    User getAuthenticatedUser();

    String refreshToken(String oldToken);
}
