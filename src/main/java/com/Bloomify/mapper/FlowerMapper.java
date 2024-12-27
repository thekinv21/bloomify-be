package com.Bloomify.mapper;

import com.Bloomify.dto.FlowerDto;
import com.Bloomify.model.Flower;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FlowerMapper {

    FlowerDto toDto(Flower entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Flower toEntity(FlowerDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(FlowerDto dto, @MappingTarget Flower entity);

}

