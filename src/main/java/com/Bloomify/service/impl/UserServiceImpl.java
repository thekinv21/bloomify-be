package com.Bloomify.service.impl;


import com.Bloomify.enums.RoleEnum;
import com.Bloomify.dto.RoleDto;
import com.Bloomify.dto.SelectDto;
import com.Bloomify.dto.UserDto;
import com.Bloomify.exception.CustomException;
import com.Bloomify.mapper.RoleMapper;
import com.Bloomify.mapper.UserMapper;
import com.Bloomify.model.User;
import com.Bloomify.repository.UserRepository;
import com.Bloomify.service.RoleService;
import com.Bloomify.service.UserService;
import com.Bloomify.spesification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final RoleMapper roleMapper;


    @Override
    public Page<UserDto> getAll(String searchTerm, Pageable pageable) {
        Specification<User> spec =
                Specification.where(
                        new GenericSpecification<User>().searchBy(List.of("firstName", "lastName", "username"), searchTerm));
        return userRepository.findAll(spec, pageable).map(userMapper::toDto);
    }

    @Override
    public List<UserDto> getAllActive() {
        return userRepository.findAllByIsActiveTrue().
                stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<SelectDto<UUID>> getForSelect() {
        return userRepository.findAllByIsActiveTrue().
                stream().map(userMapper::toSelectDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(UUID id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with ID: " + id + " not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public UserDto getActiveById(UUID id) {
        return userMapper.toDto(userRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new CustomException("Active User with ID: " + id + " not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public User getEntityById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User Entity with ID: " + id + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserDto getByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmailAndIsActiveTrue(email)
                .orElseThrow(() -> new CustomException("User with email: " + email + " not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public UserDto getByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsernameAndIsActiveTrue(username)
                .orElseThrow(() -> new CustomException("User with username: " + username + " not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public User getEntityByUsername(String username) {
        User user = userRepository.getByUsernameAndIsActiveTrue(username);

        if (user == null) {
            throw new CustomException("User with username: " + username + " not found", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @Override
    public UserDto create(UserDto dto) {

        isUnique(dto);

        User user = new User();
        if (dto.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        }
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAvatarPath(dto.getAvatarPath());

        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            user.setRoles(roleService.getRolesByNames(dto.getRoles().stream().toList()));
        } else {
            RoleDto defaultRole = roleService.getByRoleName(RoleEnum.USER.name());
            if (defaultRole != null) {
                user.setRoles(Collections.singleton(roleMapper.toEntity((defaultRole))));
            }
        }

        user.setIsActive(true);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(UserDto dto) {
        User existingUser = this.getEntityById(dto.getId());

        existingUser.setUsername(dto.getUsername());
        existingUser.setEmail(dto.getEmail());
        existingUser.setLastName(dto.getLastName());
        existingUser.setFirstName(dto.getFirstName());
        existingUser.setAvatarPath(dto.getAvatarPath());
        existingUser.setRoles(roleService.getRolesByNames(dto.getRoles().stream().toList()));
        existingUser.setIsActive(dto.isActive);

        return userMapper.toDto(userRepository.save(existingUser));
    }

    @Override
    public void toggle(UUID id) {
        User user = this.getEntityById(id);
        user.setIsActive(!user.isActive);
        userRepository.save(user);
    }


    @Override
    public void delete(UUID id) {
        this.getEntityById(id);
        userRepository.deleteById(id);
    }

    @Override
    public UUID updateTokenSign(String username) {
        User user = getEntityByUsername(username);
        UUID sign = UUID.randomUUID();
        user.setTokenSign(sign);
        userRepository.save(user);
        return sign;
    }

    public void isUnique(UserDto dto) {
        User userWithSameEmail = userRepository.findByEmail(dto.getEmail());
        User userWithSameUsername = userRepository.findByUsername(dto.getUsername());

        if (userWithSameEmail != null || userWithSameUsername != null) {
            throw new CustomException("User already exists", HttpStatus.CONFLICT);
        }
    }
}
