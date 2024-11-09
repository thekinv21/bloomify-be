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
    private String token;

    @Column
    private Instant expiryDate;

    private boolean valid;
}
