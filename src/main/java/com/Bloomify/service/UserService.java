package com.Bloomify.service;


import com.Bloomify.dto.UserDto;
import com.Bloomify.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService{

    List<UserDto> getAll();

    UserDto getById(UUID id);

    Optional<UserDto> getOptDtoById(UUID id);

    Optional<User> getOptEntityById(UUID id);

    UserDto getByEmail(String email);

    UserDto getByUsername(String username);

    UserDto create(UserDto dto);

    UserDto update(UserDto dto);

    void toggle(UUID id);

    void delete(UUID id);
}
