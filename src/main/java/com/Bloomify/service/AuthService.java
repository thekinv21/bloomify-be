package com.Bloomify.service;


import com.Bloomify.dto.*;


public interface AuthService {

    TokenDto login(LoginDto loginDto);

    UserDto getMyself();

    void logout(LogoutDto logoutDto);

    UserDto register(UserDto dto);

    TokenDto refreshToken(RefreshTokenDto refreshTokenDto);

    boolean validateOtp(OtpValidateDto otpValidateDto);
}