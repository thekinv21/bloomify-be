package com.Bloomify.service.impl;


import com.Bloomify.dto.AuthDto;
import com.Bloomify.dto.UserDto;
import com.Bloomify.model.Token;
import com.Bloomify.repository.TokenRepository;
import com.Bloomify.service.TokenService;
import com.Bloomify.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${jwt-variables.KEY}")
    private String jwtKey;
    @Value("${jwt-variables.ISSUER}")
    private String jwtIssuer;
    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Long accessTokenExpiryDuration;
    @Value("${jwt-variables.EXPIRES_REFRESH_TOKEN_MINUTE}")
    private Long refreshTokenExpiryDuration;

    private final TokenRepository tokenRepository;
    private final UserService userService;


    @Override
    public String generateAccessToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(
                        new Date(
                                System.currentTimeMillis()
                                        + Duration.ofMinutes(accessTokenExpiryDuration).toMillis()))
                .withIssuer(jwtIssuer)
                .sign(Algorithm.HMAC256(jwtKey.getBytes()));
    }

    @Override
    public String generateRefreshToken(String username) {
        Instant expirationTime = Instant.now().plus(Duration.ofMinutes(refreshTokenExpiryDuration));
        Token refreshToken = new Token();
        refreshToken.setUsername(username);
        refreshToken.setValid(true);
        refreshToken.setRefreshTokenExpiryDate(expirationTime);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken = tokenRepository.save(refreshToken);

        return refreshToken.getRefreshToken();
    }

    @Override
    public DecodedJWT verifyAccessToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey.getBytes(StandardCharsets.UTF_8));
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            return verifier.verify(jwtToken);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public AuthDto generateTokens(String username) {
        var user = userService.getByUsername(username);
        return getTokensDto(user);
    }

    @Override
    public AuthDto getTokensDto(UserDto dto) {
        var accessToken = generateAccessToken(dto.getUsername());
        var refreshToken = generateRefreshToken(dto.getUsername());
        Instant accessTokenExpiryAt = Instant.now().plus(Duration.ofMinutes(accessTokenExpiryDuration));
        Instant refreshTokenExpiryAt = Instant.now().plus(Duration.ofMinutes(refreshTokenExpiryDuration));

        return AuthDto.builder()
                .user(dto)
                .accessToken(accessToken)
                .accessTokenExpiresAt(accessTokenExpiryAt)
                .refreshToken(refreshToken)
                .refreshTokenExpiresAt(refreshTokenExpiryAt)
                .build();

    }

    @Override
    public AuthDto getNewTokens(String refreshToken) {

        // FIND EXISTING TOKEN WITH REFRESH TOKEN
        Optional<Token> isExistRefreshToken = tokenRepository.findTokenByRefreshTokenAndValidTrue(refreshToken);

        // CHECK TOKEN IS NULL OR NOT
        if (isExistRefreshToken.isEmpty()) {
            throw new EntityNotFoundException("Refresh Token is not found!");
        }
        // IF TOKEN IS HAVE: VERIFY IT
        verifyRefreshToken(isExistRefreshToken.get());

        // IF IS VERIFIED DELETE THIS TOKEN
        tokenRepository.delete(isExistRefreshToken.get());

        // GET USER BY USERNAME AND
        var user = userService.getByUsername(isExistRefreshToken.get().getUsername());
        //RETURN WITH NEW TOKENS
        return getTokensDto(user);

    }

    @Override
    public void addToBlackList(AuthDto.TokenDto dto) {
        Token token = tokenRepository.findByAccessTokenAndValidTrue(dto.getAccessToken());
        token.setValid(false);
        tokenRepository.save(token);
    }


    // VERIFY TOKEN MINUTES
    private void verifyRefreshToken(Token token) {
        if (token.getRefreshTokenExpiryDate().isBefore(Instant.now()) && !token.isValid()) {
            throw new RuntimeException("Invalid Refresh Token!");
        }
    }

}
