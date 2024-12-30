package com.Bloomify.controller;

import com.Bloomify.dto.*;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user", operationId = "registerUser")
    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse> register(@RequestBody UserDto dto) {
        return CustomApiResponse.builder().data(authService.register(dto)).build();
    }

    @Operation(summary = "Login an existing user", operationId = "loginUser")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().body(authService.login(loginDto));
    }

    @Operation(summary = "Get logged-in user details", operationId = "getMyself")
    @GetMapping("/get-myself")
    public ResponseEntity<UserDto> generateServiceToken() {
        return ResponseEntity.ok().body(authService.getMyself());
    }

    @Operation(summary = "Logout the current user", operationId = "logoutUser")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        authService.logout(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Refresh the authentication token", operationId = "refreshToken")
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDto> refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenRequest) {
        return ResponseEntity.ok().body(authService.refreshToken(refreshTokenRequest));
    }

    @Operation(summary = "Validate OTP", operationId = "validateOtp")
    @PostMapping("/validate-otp")
    public ResponseEntity<Boolean> validateOtp(@Valid @RequestBody OtpValidateDto otpValidateDto) {
        return ResponseEntity.ok().body(authService.validateOtp(otpValidateDto));
    }
}
