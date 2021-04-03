package com.c0d1red.sport.adapter.rest;

import com.c0d1red.sport.application.api.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("auth/classic")
    @ResponseStatus(HttpStatus.OK)
    private TokenDto login(@RequestBody CredentialsDto credentialsDto) {
        String token = securityService.authenticateAndCreateToken(credentialsDto.getUsername(), credentialsDto.getPassword());
        return wrapTokenFrom(token);
    }

    @PostMapping("token/refresh")
    @ResponseStatus(HttpStatus.OK)
    private TokenDto refreshToken(@RequestBody TokenDto tokenDto) {
        String token = securityService.refreshToken(tokenDto.getToken());
        return wrapTokenFrom(token);
    }

    private TokenDto wrapTokenFrom(String token) {
        return new TokenDto(token);
    }
}
