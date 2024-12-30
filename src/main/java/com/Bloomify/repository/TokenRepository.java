package com.Bloomify.repository;

import com.Bloomify.model.Token;
import com.Bloomify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT t FROM Token t WHERE t.user = :user ORDER BY t.createdAt ASC")
    Token findOldestTokenByUser(@Param("user") User user);

    Optional<Token> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);

    Optional<Token> findByTokenSign(String tokenSign);

    void deleteByTokenSign(String tokenSign);

    int countByUser(User user);
}
