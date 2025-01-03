package com.Bloomify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flower extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, length = 4000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String imageUrl;

    private int height;
    private int width;

    @Column(name = "flower_order")
    private Integer order;
    private Boolean isActive;

    @OneToMany(
            mappedBy = "flower",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<FlowerImage> flowerImages = new ArrayList<>();


    public void defaultIsActive() {
        if (isActive == null) {
            isActive = Boolean.TRUE;
        }
    }
}
