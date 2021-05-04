package com.c0d1red.sport.application.impl;

import com.c0d1red.sport.application.api.AnonymousUserException;
import com.c0d1red.sport.application.api.SecurityService;
import com.c0d1red.sport.domain.user.User;
import com.c0d1red.sport.domain.user.UserRepository;
import com.c0d1red.sport.infrastrcture.jwt.JwtUser;
import com.c0d1red.sport.infrastrcture.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public String authenticateAndCreateToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        JwtUser authenticatedUser = (JwtUser) authentication.getPrincipal();
        return jwtUtil.createTokenFor(authenticatedUser);
    }

    @Override
    public String refreshToken(String oldToken) {
        JwtUser tokenUser = (JwtUser) jwtUtil.getAuthentication(oldToken).getPrincipal();
        return jwtUtil.createTokenFor(tokenUser);
    }

    @Override
    public User getAuthenticatedUser() {
        long userId = getIdForAuthenticatedUser();
        return userRepository.getUserById(userId);
    }

    private long getIdForAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")) {
            throw new AnonymousUserException();
        }
        JwtUser authenticatedUser = (JwtUser) principal;
        return authenticatedUser.getId();
    }
}
