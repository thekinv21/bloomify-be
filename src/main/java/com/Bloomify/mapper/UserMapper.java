package com.Bloomify.mapper;

import com.Bloomify.dto.SelectDto;
import com.Bloomify.dto.UserDto;
import com.Bloomify.model.Role;
import com.Bloomify.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping( target = "label", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    @Mapping(source = "id", target = "value")
    SelectDto<UUID> toSelectDto(User user);

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto dto);

    default Set<String> rolesToRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}

