package com.Bloomify.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity(name = "TOKEN")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(nullable = false, unique = true, length = 2000)
    private String accessToken;

    @Column(nullable = false, unique = true, length = 2000)
    private String refreshToken;

    @Column
    private Instant accessTokenExpiryDate;

    @Column
    private Instant refreshTokenExpiryDate;

    private boolean valid;
}
