package com.Bloomify.service;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface JwtEncoderService {

    String generateToken(Authentication authentication, UUID tokenSign);

}
