package com.Bloomify.service.impl;


import com.Bloomify.dto.RoleDto;
import com.Bloomify.dto.SelectDto;
import com.Bloomify.exception.CustomException;
import com.Bloomify.mapper.RoleMapper;
import com.Bloomify.model.Role;
import com.Bloomify.repository.RoleRepository;
import com.Bloomify.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;


    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().
                stream().map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> getAllActive() {
        return roleRepository.findAllByIsActiveTrue().
                stream().map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SelectDto<Long>> getForSelect() {
        return roleRepository.findAllByIsActiveTrue().
                stream().map(roleMapper::toSelectDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto getById(Long id) {
        return roleMapper.toDto(roleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Role with ID: " + id + " not found", HttpStatus.NOT_FOUND)));

    }

    @Override
    public RoleDto getActiveById(Long id) {
        Role role = roleRepository.findByIdAndIsActiveTrue(id);
        if (role == null) {
            throw new CustomException("Role with ID: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return roleMapper.toDto(role);
    }

    @Override
    public Role getRoleEntityById(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new CustomException("Role with ID: " + id + " not found", HttpStatus.NOT_FOUND)
        );
    }


    @Override
    public RoleDto getByRoleName(String roleName) {
        return roleMapper.toDto(roleRepository.findByNameAndIsActiveTrue(roleName).orElseThrow(
                () -> new CustomException("Role with name: " + roleName + " not found", HttpStatus.NOT_FOUND)
        ));
    }

    @Override
    public Set<Role> getRolesByNames(List<String> roleNames) {
        return roleRepository.findAllByNameInAndIsActiveTrue(roleNames);
    }

    @Override
    public RoleDto create(RoleDto dto) {
        Optional<Role> isExistRole = roleRepository.findByNameAndIsActiveTrue(dto.getName());

        if (isExistRole.isPresent()) {
            throw new CustomException("Role with name: " + dto.getName() + " already exists", HttpStatus.CONFLICT);
        }
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(dto)));
    }

    @Override
    public RoleDto update(RoleDto dto) {
        this.getById(dto.getId());
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(dto)));
    }

    @Override
    public void toggle(Long id) {
        Role role = getRoleEntityById(id);
        role.setIsActive(!role.getIsActive());
        roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        this.getRoleEntityById(id);
        roleRepository.deleteById(id);
    }
}
