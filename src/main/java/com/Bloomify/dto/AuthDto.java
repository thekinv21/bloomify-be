package com.Bloomify.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthDto {
    private UserDto user;
    private TokenDto tokens;

    @Data
    public static class LoginDto {
        private String username;
        private String password;
    }

    @Data
    public static class TokenDto {
        private String accessToken;
        private String refreshToken;
        private LocalDateTime expiresAt;
    }
}
