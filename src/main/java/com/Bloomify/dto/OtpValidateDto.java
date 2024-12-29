package com.Bloomify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OtpValidateDto {
    @NotBlank
    private String tokenSign;
    @NotNull
    private Integer otpCode;
}
