package com.c0d1red.sport.infrastrcture.jwt;

import com.c0d1red.sport.domain.user.User;
import com.c0d1red.sport.domain.user.UserRepository;
import com.c0d1red.sport.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(Username.from(username));
        return JwtUserFactory.createFrom(user);
    }

}
