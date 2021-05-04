package com.c0d1red.sport.infrastrcture.jwt;

import com.c0d1red.sport.domain.user.User;
import com.c0d1red.sport.infrastrcture.AuthFailedException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private static final String CLAIM_KEY_ID = "id";
    private static final String CLAIM_KEY_ROLE = "role";
    private static final String EMPTY_CREDENTIALS = "";
    private final JwtUserDetailsService jwtUserDetailsService;
    @Value("${jwt.expired}")
    private long tokenValidityInMilliseconds;
    @Value("${jwt.secret}")
    private String secret;

    public String createTokenFor(JwtUser jwtUser) {
        Claims claims = createClaims(jwtUser.getId(), jwtUser.getUsername(), jwtUser.getRole());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + tokenValidityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(HS256, secret)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Date now = new Date();
            boolean isTokenExpired = claims.getBody().getExpiration().before(now);
            return !isTokenExpired;
        } catch (JwtException | IllegalArgumentException | NullPointerException e) {
            throw new AuthFailedException("JWT is invalid");
        }
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        JwtUser jwtUser = jwtUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(jwtUser, EMPTY_CREDENTIALS, jwtUser.getAuthorities());
    }

    private Claims createClaims(Long id, String username, User.Role userRole) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(CLAIM_KEY_ID, id);
        claims.put(CLAIM_KEY_ROLE, userRole);
        return claims;
    }

    private String getUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            log.warn("Token {} has been expired", token);
            return e.getClaims().getSubject();
        }
    }

}
