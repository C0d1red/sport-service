package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.application.api.UserService;
import com.c0d1red.sport.application.impl.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private TokenDto register(@RequestBody CredentialsDto credentialsDto) {
        UserCreateRequest userCreateRequest = UserRequestFactory.createFrom(credentialsDto);
        String token = userService.createNewUser(userCreateRequest);
        return wrapTokenFrom(token);
    }

    private TokenDto wrapTokenFrom(String token) {
        return new TokenDto(token);
    }
}
