package com.Bloomify.service;

import com.Bloomify.dto.RoleDto;
import com.Bloomify.dto.SelectDto;
import com.Bloomify.dto.UserDto;
import com.Bloomify.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Page<RoleDto> getAll(String searchTerm, Pageable pageable);

    List<RoleDto> getAllActive();

    List<SelectDto<Long>> getForSelect();

    RoleDto getById(Long id);

    RoleDto getActiveById(Long id);

    Role getRoleEntityById(Long id);

    RoleDto getByRoleName(String roleName);

    Set<Role> getRolesByNames(List<String> roleNames);

    RoleDto create(RoleDto dto);

    RoleDto update(RoleDto dto);

    void toggle(Long id);

    void delete(Long id);
}
