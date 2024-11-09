package com.Bloomify.controller;

import com.Bloomify.dto.AuthDto;
import com.Bloomify.dto.UserDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "Login a user", operationId = "loginUser")
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse> login(@RequestBody AuthDto.LoginDto dto) {
        return CustomApiResponse.builder().data(authService.login(dto)).build();
    }

    @Operation(summary = "Refresh authentication token", operationId = "refreshToken")
    @PostMapping("/refreshToken")
    public ResponseEntity<CustomApiResponse> refreshToken(@RequestBody AuthDto.RefreshTokenDto dto) {
        return CustomApiResponse.builder().data(authService.refreshToken(dto)).build();
    }

    @Operation(summary = "Logout the current user", operationId = "logoutUser")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody AuthDto.TokenDto dto) {
        authService.logout(dto);
        return ResponseEntity.ok().build();
    }
}
