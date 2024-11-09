package com.Bloomify.security;

import com.Bloomify.mapper.UserMapper;
import com.Bloomify.model.User;
import com.Bloomify.repository.UserRepository;
import com.Bloomify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UserDetailsSecurityService implements UserDetailsService {

    private final UserService userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsSecurity(userService.getEntityByUsername(username));
    }
}
