package com.Bloomify.mapper;


import com.Bloomify.dto.FlowerImageDto;
import com.Bloomify.model.FlowerImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlowerImageMapper {

    FlowerImageDto toDto(FlowerImage entity);
    FlowerImage toEntity(FlowerImageDto dto);

}

