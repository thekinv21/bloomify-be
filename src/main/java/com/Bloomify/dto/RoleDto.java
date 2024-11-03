package com.Bloomify.dto;

import com.Bloomify.validation.CreateValidation;
import com.Bloomify.validation.UpdateValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RoleDto {

    @NotNull(groups = UpdateValidation.class)
    @Null(groups = CreateValidation.class)
    public Long id;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String name;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public Boolean isActive;
}
