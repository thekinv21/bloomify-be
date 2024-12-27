package com.Bloomify.dto;

import com.Bloomify.validation.CreateValidation;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class BaseDto {

    @Null(groups = {CreateValidation.class})
    private LocalDateTime createdAt;

    @Null(groups = {CreateValidation.class})
    private LocalDateTime updatedAt;

}


