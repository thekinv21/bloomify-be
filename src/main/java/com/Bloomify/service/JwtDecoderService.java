package com.Bloomify.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public interface JwtDecoderService {

    String extractUsername(String token);

    List<String> extractRoles(String token);

    String extractTokenSign(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token, String expectedSignature);

}
