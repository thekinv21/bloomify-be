package com.Bloomify.service;


import com.Bloomify.dto.*;


public interface AuthService {

    TokenDto.TokenSignDto login(LoginDto loginDto);

    UserDto getMyself();

    void logout(String httpAuthorizationHeader);

    UserDto register(UserDto dto);

    TokenDto refreshToken(RefreshTokenDto refreshTokenDto);

    boolean validateOtp(OtpValidateDto otpValidateDto);
}