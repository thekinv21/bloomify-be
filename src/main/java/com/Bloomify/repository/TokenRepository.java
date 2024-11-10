package com.Bloomify.repository;

import com.Bloomify.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByRefreshTokenAndValidTrue(String accessToken);

    Token findByAccessTokenAndValidTrue(String accessToken);

    Token findByAccessToken(String accessToken);

}
