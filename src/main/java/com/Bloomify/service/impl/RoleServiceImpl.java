package com.Bloomify.service.impl;


import com.Bloomify.dto.RoleDto;
import com.Bloomify.mapper.RoleMapper;
import com.Bloomify.model.Role;
import com.Bloomify.repository.RoleRepository;
import com.Bloomify.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.Bloomify.exception.ExceptionUtil.buildException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;


    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAllByIsActiveTrue().
                stream().map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto getById(Long id) {
        return roleMapper.toDto(roleRepository.findByIdAndIsActiveTrue(id));
    }

    @Override
    public RoleDto getByRoleName(String roleName) {
        return roleMapper.toDto(roleRepository.findByNameAndIsActiveTrue(roleName).orElseThrow(
                () -> buildException("Role with name " + roleName + " not found")
        ));
    }

    @Override
    public Set<Role> getRolesByNames(List<String> roleNames) {
        return roleRepository.findAllByNameInAndIsActiveTrue(roleNames);
    }

    @Override
    public RoleDto create(RoleDto dto) {
        return null;
    }

    @Override
    public RoleDto update(RoleDto dto) {
        return null;
    }

    @Override
    public void toggle(Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
