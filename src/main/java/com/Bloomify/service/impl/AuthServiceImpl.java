package com.Bloomify.service.impl;

import com.Bloomify.dto.*;
import com.Bloomify.exception.CustomException;
import com.Bloomify.model.User;
import com.Bloomify.security.CustomUserDetails;
import com.Bloomify.security.CustomUsernamePasswordAuthenticationToken;
import com.Bloomify.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final EmailSender emailSender;

    public TokenDto.TokenSignDto login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword())
            );
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();
            TokenDto token = tokenService.createNewTokenForUser(user);

            if (Boolean.TRUE.equals(user.getOtpEnabled())) {
                Integer otpCode = tokenService.createOtp(token.getTokenSign());
                log.info("otp: {}, created for user {}", otpCode, loginDto.getUsername());
                HtmlEmailDto htmlEmailDto = new HtmlEmailDto("OTP Verification", user.getEmail(), "otp-mail-modern");
                Map<String, Object> model = new HashMap<>();
                model.put("name", user.getUsername());
                model.put("otp_code", otpCode);
                emailSender.htmlSend(htmlEmailDto, model);
            }
            TokenDto.TokenSignDto tokenSignDto = new TokenDto.TokenSignDto();
            tokenSignDto.setTokenSign(token.getTokenSign());
            return tokenSignDto;
        } catch (Exception e) {
            log.error("Error occurred during login for user: {}", loginDto.getUsername(), e);
            throw new CustomException("Wrong Credentials", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserDto getMyself() {
        CustomUsernamePasswordAuthenticationToken jwtAuthentication;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jwtAuthentication = (CustomUsernamePasswordAuthenticationToken) authentication;
        UserDto user = userService.getByUsername(jwtAuthentication.getName());
        String jwtToken = jwtAuthentication.getToken();
        user.setToken(jwtToken);
        return user;
    }

    @Override
    public void logout(String httpAuthorizationHeader) {
        tokenService.invalidateTokenByAuthorizationHeader(httpAuthorizationHeader);

    }

    @Override
    public UserDto register(UserDto dto) {
        return userService.create(dto);
    }


    @Override
    public TokenDto refreshToken(RefreshTokenDto refreshTokenDto) {
        return tokenService.createNewTokenWithRefreshToken(refreshTokenDto.getRefreshToken());
    }


    @Override
    public TokenDto validateOtp(OtpValidateDto otpValidateDto) {
        return tokenService.isOtpValid(otpValidateDto);
    }

}
