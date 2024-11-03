package com.Bloomify.service.impl;

import com.Bloomify.dto.UserDto;
import com.Bloomify.mapper.UserMapper;
import com.Bloomify.model.User;
import com.Bloomify.repository.UserRepository;
import com.Bloomify.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDto, UUID> implements UserService {
    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }
}
