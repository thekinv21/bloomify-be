package com.Bloomify.service.impl;

import com.Bloomify.dto.AuthDto;
import com.Bloomify.dto.UserDto;
import com.Bloomify.exception.CustomException;
import com.Bloomify.model.Token;
import com.Bloomify.repository.TokenRepository;
import com.Bloomify.service.AuthService;
import com.Bloomify.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;


    @Override
    public AuthDto login(AuthDto.LoginDto dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );
            return tokenService.generateTokens(dto.getUsername());

        } catch (Exception exception) {
            throw new CustomException("Wrong Credentials", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public AuthDto register(UserDto dto) {
        return null;
    }


    @Override
    public AuthDto refreshToken(AuthDto.TokenDto dto) {
        return tokenService.getNewTokens(dto.getRefreshToken());
    }

    @Override
    public void logout(AuthDto.TokenDto dto) {
        try {
            Token token = tokenRepository.findByAccessToken(dto.getAccessToken());
            if (token != null && token.isValid()) {
                token.setValid(false);
                tokenRepository.save(token);
            }
        }
        catch (Exception exception) {
            throw new CustomException("Invalid Token", HttpStatus.FORBIDDEN);
        }
    }
}
