package com.Bloomify.service;


import com.Bloomify.dto.*;


public interface AuthService {

    TokenDto login(LoginRequest loginRequest);

    UserDto getMyself();

    void logout(LogoutRequest logoutRequest);

    UserDto register(UserDto dto);
}