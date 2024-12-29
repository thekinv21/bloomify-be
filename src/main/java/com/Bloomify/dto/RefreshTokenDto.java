package com.Bloomify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class RefreshTokenDto {
    @NotBlank
    private String refreshToken;
}
