package com.Bloomify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String imageTitle;

    public String imageCost;

    @Column(nullable = false)
    private String imageUrl;

    private Boolean isActive;
    private Boolean isMainImage;

    @Column(name = "flower_image_order")
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id", nullable = false)
    private Flower flower;


    public void defaultIsActive() {
        if (isActive == null) {
            isActive = Boolean.TRUE;
        }

        if (isMainImage == null) {
            isMainImage = Boolean.FALSE;
        }
    }
}
