package com.Bloomify.service;


import com.Bloomify.dto.AuthDto;
import com.Bloomify.dto.UserDto;



public interface AuthService {

    AuthDto register(UserDto dto);

    AuthDto login(AuthDto.LoginDto dto);

    AuthDto refreshToken(AuthDto.TokenDto dto);

    void logout(AuthDto.TokenDto dto);
}
