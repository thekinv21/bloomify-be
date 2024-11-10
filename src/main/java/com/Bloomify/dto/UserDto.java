package com.Bloomify.dto;
import com.Bloomify.validation.CreateValidation;
import com.Bloomify.validation.UpdateValidation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserDto extends BaseDto {

    @NotNull(groups = UpdateValidation.class)
    @Null(groups = CreateValidation.class)
    public UUID id;

    public String firstName;
    public String lastName;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String username;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String email;

    @NotNull(groups = CreateValidation.class)
    public String password;

    public String avatarPath;

    public boolean isActive;

    @NotNull(groups = {CreateValidation.class, UpdateValidation.class})
    private Set<String> roles;
}
