package ru.chaplyginma.SpringSecurityJwtHomework.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.RefreshTokenExpiredException;
import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final String ROLES_CLAIM = "roles";

    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.accessTokenExpiration}")
    private Duration accessTokenExpiration;
    @Value("${jwt.refreshTokenExpiration}")
    private Duration refreshTokenExpiration;

    public String generateAccessToken(String username, Set<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(
                ROLES_CLAIM,
                roles.stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
        );


        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration.toMillis()))
                .subject(username)
                .claims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public RefreshToken getNewRefreshToken(User user) {
        RefreshToken newRefreshToken = generateRefreshToken(user);
        return refreshTokenService.save(newRefreshToken);
    }

    public RefreshToken generateRefreshToken(User user) {
        return RefreshToken.builder()
                .user(user)
                .tokenValue(UUID.randomUUID().toString())
                .expireAt(LocalDateTime.now().plus(refreshTokenExpiration))
                .build();
    }

    public RefreshToken getRefreshTokenByValue(String value) {
        return refreshTokenService.findByValue(value);
    }

    public void validateRefreshToken(RefreshToken refreshToken) {
        if (refreshToken.getExpireAt().isBefore(LocalDateTime.now())) {
            refreshTokenService.delete(refreshToken);
            throw new RefreshTokenExpiredException("Refresh token expired");
        }
    }

    public String extractUserName(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
