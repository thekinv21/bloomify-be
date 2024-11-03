package com.Bloomify.service;

import com.Bloomify.dto.UserDto;
import com.Bloomify.model.User;

import java.util.UUID;

public interface UserService extends BaseService<User, UserDto, UUID>{}
