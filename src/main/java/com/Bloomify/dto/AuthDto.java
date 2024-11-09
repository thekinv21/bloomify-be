package com.Bloomify.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthDto {
    private UserDto user;
    private String accessToken;
    private String refreshToken;
    private Instant accessTokenExpiresAt;
    private Instant refreshTokenExpiresAt;

    @Data
    public static class LoginDto {
        private String username;
        private String password;
    }

    @Data
    public static class TokenDto {
        private String accessToken;
        private String refreshToken;
        private Instant accessTokenExpiresAt;
        private Instant refreshTokenExpiresAt;
    }
}
