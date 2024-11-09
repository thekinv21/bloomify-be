package com.Bloomify.service;

import com.Bloomify.dto.UserDto;
import com.Bloomify.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService{

    List<UserDto> getAll();

    List<UserDto> getAllActive();

    UserDto getById(UUID id);

    UserDto getActiveById(UUID id);

    User getEntityById(UUID id);

    UserDto getByEmail(String email);

    UserDto getByUsername(String username);

    UserDto create(UserDto dto);

    UserDto update(UserDto dto);

    void toggle(UUID id);

    void delete(UUID id);
}
