package com.Bloomify.dto;

import com.Bloomify.validation.CreateValidation;
import com.Bloomify.validation.UpdateValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoleDto extends BaseDto {

    @NotNull(groups = UpdateValidation.class)
    @Null(groups = CreateValidation.class)
    public Long id;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String name;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public Boolean isActive;


    public RoleDto(String name) {
        this.name = name;
        this.isActive = true;
    }
}
