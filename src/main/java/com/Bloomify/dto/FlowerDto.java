package com.Bloomify.dto;


import com.Bloomify.validation.CreateValidation;
import com.Bloomify.validation.UpdateValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter

public class FlowerDto extends BaseDto {
    @NotNull(groups = UpdateValidation.class)
    @Null(groups = CreateValidation.class)
    public Long id;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public String title;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public String description;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public BigDecimal price;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public String currency;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public String imageUrl;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public int height;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public int width;

    public Boolean isActive;

    @NotNull(groups = {UpdateValidation.class, CreateValidation.class})
    public List<FlowerImageDto> images;
}
