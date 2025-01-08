package com.Bloomify.dto;

import lombok.*;

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


    @Getter
    @Setter
    public static class TokenSignDto {
        private String tokenSign;
    }
}
