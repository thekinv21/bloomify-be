package com.Bloomify.mapper;

import com.Bloomify.dto.RoleDto;
import com.Bloomify.dto.SelectDto;
import com.Bloomify.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    @Mapping(source = "name", target = "label")
    @Mapping(source = "id", target = "value")
    SelectDto<Long> toSelectDto(Role role);

    RoleDto toDto(Role entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role toEntity(RoleDto dto);

}

