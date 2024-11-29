package com.Bloomify.service.impl;

import com.Bloomify.dto.*;
import com.Bloomify.security.CustomUsernamePasswordAuthenticationToken;
import com.Bloomify.service.AuthService;
import com.Bloomify.service.JwtDecoderService;
import com.Bloomify.service.JwtEncoderService;
import com.Bloomify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtEncoderService tokenService;
    private final JwtDecoderService jwtDecoderService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public TokenDto login(LoginRequest loginRequest) {
        var authObj = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));

        // update token sign on login
        var sign = userService.updateTokenSign(loginRequest.getUsername());

        var user = userService.getByUsername(loginRequest.getUsername());
        return new TokenDto(tokenService.generateToken(authObj, sign), user);
    }

    @Override
    public UserDto getMyself() {
        CustomUsernamePasswordAuthenticationToken jwtAuthentication;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        jwtAuthentication = (CustomUsernamePasswordAuthenticationToken) authentication;
        var user = userService.getByUsername(jwtAuthentication.getName());
        String jwtToken = jwtAuthentication.getToken();
        user.setToken(jwtToken);
        return user;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        var username = jwtDecoderService.extractUsername(logoutRequest.getToken());
        // update token sign on logout
        userService.updateTokenSign(username);
    }

    @Override
    public UserDto register(UserDto dto) {
        return userService.create(dto);
    }

}
