package com.Bloomify.controller;

import com.Bloomify.dto.*;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @GetMapping("/get-myself")
    public ResponseEntity<UserDto> generateServiceToken() {
        return ResponseEntity.ok().body(authService.getMyself());
    }

    @PostMapping("/logout")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return ResponseEntity.ok().build();
    }
}
