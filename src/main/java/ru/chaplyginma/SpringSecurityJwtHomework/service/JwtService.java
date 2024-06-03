package ru.chaplyginma.SpringSecurityJwtHomework.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final String ROLES_CLAIM = "roles";
    private static final String ID_CLAIM = "id";

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.accessTokenExpiration}")
    private Duration accessTokenExpiration;
    @Value("${jwt.refreshTokenExpiration}")
    private Duration refreshTokenExpiration;

    public String generateAccessToken(String userName, Long id, Set<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID_CLAIM, id);
        claims.put(
                ROLES_CLAIM,
                roles.stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
        );


        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration.toMillis()))
                .subject(userName)
                .claims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
