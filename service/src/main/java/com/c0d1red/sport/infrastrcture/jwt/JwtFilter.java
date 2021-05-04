package com.c0d1red.sport.infrastrcture.jwt;

import com.c0d1red.sport.infrastrcture.AuthFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            authenticate((HttpServletRequest) servletRequest);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthFailedException e) {
            sendForbiddenError((HttpServletResponse) servletResponse);
            log.error("JWT forbidden error has been send");
        }
    }

    private void authenticate(HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);
        if (token != null && jwtUtil.isTokenValid(token)) {
            Authentication authentication = jwtUtil.getAuthentication(token);
            setAuthentication(Optional.ofNullable(authentication));
        }
    }

    private void setAuthentication(Optional<Authentication> authentication) {
        authentication.ifPresent(
                auth -> SecurityContextHolder.getContext().setAuthentication(auth)
        );
    }

    private void sendForbiddenError(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendError(FORBIDDEN.value());
    }
}
