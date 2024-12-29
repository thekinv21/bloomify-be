package com.Bloomify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiryDate;
    private LocalDateTime accessTokenExpiryDate;
    private UserDto user;
}
