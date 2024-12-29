package com.Bloomify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogoutDto {
    @NotBlank
    private String token;
}
