package com.Bloomify.service.impl;


import com.Bloomify.dto.UserDto;
import com.Bloomify.exception.CustomException;
import com.Bloomify.mapper.UserMapper;
import com.Bloomify.model.User;
import com.Bloomify.repository.UserRepository;
import com.Bloomify.service.RoleService;
import com.Bloomify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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


    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().
                stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllActive() {
        return userRepository.findAllByIsActiveTrue().
                stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserDto getById(UUID id) {
        return this.getOptDtoById(id);
    }

    @Override
    public UserDto getOptDtoById(UUID id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with ID: " + id + " not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public User getOptEntityById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User with ID: " + id + " not found", HttpStatus.NOT_FOUND));
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
        user.setRoles(roleService.getRolesByNames(dto.getRoles()
                .stream().toList()));
        user.setIsActive(true);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(UserDto dto) {
        User existingUser = getOptEntityById(dto.getId());

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
        UserDto user = this.getById(id);
        user.setActive(!user.isActive);
        userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public void delete(UUID id) {
        this.getOptEntityById(id);
        userRepository.deleteById(id);
    }


    public void isUnique(UserDto dto) {
        User userWithSameEmail = userRepository.findByEmail(dto.getEmail());
        User userWithSameUsername = userRepository.findByUsername(dto.getUsername());

        if (userWithSameEmail != null || userWithSameUsername != null) {
            System.out.println("User with the same email or username already exists");
            throw new CustomException("User already exists", HttpStatus.CONFLICT);
        }
    }
}
