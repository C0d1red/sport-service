package com.c0d1red.sport.infrastrcture.jwt;


import com.c0d1red.sport.domain.user.User;

public class JwtUserFactory {

    public static JwtUser createFrom(User user) {
        Long userId = user.getId();
        String username = user.getUsername().getName();
        String password = user.getPassword().getHash();
        User.Role userRole = user.getRole();
        return new JwtUser(userId, username, password, userRole);
    }

}
