package com.c0d1red.sport.domain.user;

import com.c0d1red.sport.application.impl.UserCreateRequest;
import com.c0d1red.sport.domain.user.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createFrom(UserCreateRequest userCreateRequest) {
        Username username = Username.from(userCreateRequest.getRawUsername());
        Password password = createEncryptedPasswordFrom(userCreateRequest.getRawPassword());
        Role role = userCreateRequest.getRole();
        return new User(username, password, role);
    }

    private Password createEncryptedPasswordFrom(String rawPassword) {
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        return Password.from(encodedPassword);
    }
}
