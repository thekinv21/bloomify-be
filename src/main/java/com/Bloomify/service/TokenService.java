package com.Bloomify.service;


import com.Bloomify.dto.AuthDto;
import com.Bloomify.dto.UserDto;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface TokenService {

    DecodedJWT verifyAccessToken(String jwtToken);

    String generateAccessToken(String username);

    String generateRefreshToken(String username);

    AuthDto generateTokens(String username);

    AuthDto getNewTokens(String refreshToken);

    AuthDto getTokensDto(UserDto dto);

    void addToBlackList(AuthDto.TokenDto dto);
}
