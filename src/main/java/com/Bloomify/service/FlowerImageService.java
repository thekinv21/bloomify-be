package com.Bloomify.service;

import com.Bloomify.dto.FlowerImageDto;
import com.Bloomify.model.FlowerImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlowerImageService {

    Page<FlowerImageDto> getAll(String searchTerm, Pageable pageable);

    Page<FlowerImageDto> getAllActive(String searchTerm, Pageable pageable);

    FlowerImageDto getById(Long id);

    FlowerImageDto getActiveById(Long id);

    FlowerImage getFlowerImageEntityById(Long id);

    FlowerImageDto create(FlowerImageDto dto);

    FlowerImageDto update(FlowerImageDto dto);

    void toggle(Long id);

    void delete(Long id);

    List<FlowerImageDto> getActiveFlowerImagesByFlowerId(Long flowerId);
}
