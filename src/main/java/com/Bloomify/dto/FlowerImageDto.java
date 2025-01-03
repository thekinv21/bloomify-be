package com.Bloomify.dto;


import com.Bloomify.validation.CreateValidation;
import com.Bloomify.validation.UpdateValidation;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FlowerImageDto extends BaseDto {

    @NotNull(groups = UpdateValidation.class)
    @Null(groups = CreateValidation.class)
    public Long id;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String imageTitle;

    public String imageCost;

    @NotNull(groups = {UpdateValidation.class,CreateValidation.class})
    public String imageUrl;

    public Boolean isMainImage;

    public Boolean isActive;
    public Integer order;

}
