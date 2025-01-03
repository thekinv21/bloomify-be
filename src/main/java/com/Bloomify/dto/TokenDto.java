package com.Bloomify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String tokenSign;
    private Boolean otpIsRequired = false;
    private String accessToken;
    private String refreshToken;
    private UserDto user;
}
