package com.Bloomify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FLOWER_IMAGE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    private Boolean isActive;

}
