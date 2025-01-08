package com.Bloomify.service;


import com.Bloomify.dto.OtpValidateDto;
import com.Bloomify.dto.TokenDto;
import com.Bloomify.model.User;

public interface TokenService {

  TokenDto createNewTokenForUser(User user);

  TokenDto createNewTokenWithRefreshToken(String refreshToken);

  void invalidateTokenSignature(String signature);

  String validateTokenAndReturnUsername(String token);

  void invalidateTokenByAuthorizationHeader(String httpAuthorizationHeader);

  Integer createOtp(String tokenSign);

  TokenDto isOtpValid(OtpValidateDto otpValidateDto);
}