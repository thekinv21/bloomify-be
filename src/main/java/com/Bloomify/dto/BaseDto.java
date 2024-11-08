package com.Bloomify.dto;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class BaseDto {

    @Null
    private LocalDateTime createdAt;

    @Null
    private LocalDateTime updatedAt;

}
