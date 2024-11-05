package com.Bloomify.service.impl;


import com.Bloomify.dto.UserDto;
import com.Bloomify.mapper.UserMapper;
import com.Bloomify.model.User;
import com.Bloomify.repository.UserRepository;
import com.Bloomify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final UserMapper userMapper;


    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().
                stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(UUID id) {
        return userRepository.findById(id).map(userMapper::toDto)
                .orElse(null);
    }

    @Override
    public Optional<UserDto> getOptDtoById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getOptEntityById(UUID id) {
        return Optional.empty();
    }

    @Override
    public UserDto getByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto getByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto create(UserDto dto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(dto)));
    }

    @Override
    public UserDto update(UserDto dto) {
        this.getById(dto.getId());
        return userMapper.toDto(userRepository.save(userMapper.toEntity(dto)));
    }

    @Override
    public void toggle(UUID id) {
        UserDto user = this.getById(id);
        user.isActive = !user.isActive;
        userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public void delete(UUID id) {
        this.getById(id);
        userRepository.deleteById(id);
    }

}
