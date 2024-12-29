package com.Bloomify.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tokens")
@Getter
@Setter

public class Token extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String tokenSign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Date refreshTokenExpiration;

    private String refreshToken;

    private Date otpExpiration;

    private Integer otpCode;

    private Boolean otpValidated = false;

}
