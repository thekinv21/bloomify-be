package com.Bloomify.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ImageDto {
    public String imageTitle;
    public String imageCost;
    public String imageUrl;
}
