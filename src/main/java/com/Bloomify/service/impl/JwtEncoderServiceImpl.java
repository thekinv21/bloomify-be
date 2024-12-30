package com.Bloomify.service.impl;

import com.Bloomify.service.JwtEncoderService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.*;

@Service
public class JwtEncoderServiceImpl implements JwtEncoderService {

    private final Key signingKey;
    private final long jwtExpiration;


    public JwtEncoderServiceImpl(
            @Value("${jwt-variables.secret-key}") String secretKey,
            @Value("${jwt-variables.expiration-time}") long jwtExpiration
    ) {
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.jwtExpiration = jwtExpiration;
    }

    @Override
    public String generateToken(String username, List<String> roles, String tokenSign) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("tokenSign", tokenSign);
        return buildToken(claims, username);
    }

    private String buildToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
