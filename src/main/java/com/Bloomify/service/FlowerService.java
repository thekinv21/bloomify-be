package com.Bloomify.service;

import com.Bloomify.dto.FlowerDto;
import com.Bloomify.model.Flower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlowerService {

    Page<FlowerDto> getAll(String searchTerm, Pageable pageable);

    Page<FlowerDto> getAllActive(String searchTerm, Pageable pageable);

    FlowerDto getById(Long id);

    FlowerDto getActiveById(Long id);

    Flower getFlowerEntityById(Long id);

    FlowerDto create(FlowerDto dto);

    FlowerDto update(FlowerDto dto);

    void toggle(Long id);

    void delete(Long id);
}
