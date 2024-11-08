package com.Bloomify.mapper;

import com.Bloomify.dto.RoleDto;
import com.Bloomify.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    RoleDto toDto(Role entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role toEntity(RoleDto dto);

}

