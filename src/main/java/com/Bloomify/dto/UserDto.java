package com.Bloomify.dto;
import com.Bloomify.validation.CreateValidation;
import com.Bloomify.validation.UpdateValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class UserDto extends BaseDto {

    @NotNull(groups = UpdateValidation.class)
    @Null(groups = CreateValidation.class)
    public String id;

    public String firstName;
    public String lastName;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String username;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String email;

    @NotNull(groups = CreateValidation.class)
    public String password;

    public String avatarPath;

    public Boolean isActive;

    @NotNull(groups = {CreateValidation.class, UpdateValidation.class})
    public List<RoleDto> roles;
}
