package com.Bloomify.service.impl;


import com.Bloomify.dto.FlowerImageDto;
import com.Bloomify.exception.CustomException;
import com.Bloomify.mapper.FlowerImageMapper;
import com.Bloomify.model.FlowerImage;
import com.Bloomify.repository.FlowerImageRepository;
import com.Bloomify.service.FlowerImageService;
import com.Bloomify.spesification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlowerImageServiceImpl implements FlowerImageService {

    private final FlowerImageMapper flowerImageMapper;
    private final FlowerImageRepository flowerImageRepository;

    @Override
    public Page<FlowerImageDto> getAll(String searchTerm, Pageable pageable) {
        Specification<FlowerImage> spec =
                Specification.where(
                        new GenericSpecification<FlowerImage>().searchBy(List.of("imageUrl", "imageTitle"), searchTerm));
        return flowerImageRepository.findAll(spec, pageable).map(flowerImageMapper::toDto);
    }

    @Override
    public Page<FlowerImageDto> getAllActive(String searchTerm, Pageable pageable) {
        Specification<FlowerImage> spec = Specification
                .where(new GenericSpecification<FlowerImage>()
                        .searchBy(List.of("imageUrl", "imageTitle"), searchTerm))
                .and((root, query, criteriaBuilder) ->
                        criteriaBuilder.isTrue(root.get("isActive"))
                );

        return flowerImageRepository.findAll(spec, pageable).map(flowerImageMapper::toDto);
    }


    @Override
    public FlowerImageDto getById(Long id) {
        return flowerImageMapper.toDto(flowerImageRepository.findById(id)
                .orElseThrow(() -> new CustomException("FlowerImage with ID: " + id + " not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public FlowerImageDto getActiveById(Long id) {
        FlowerImage flowerImage = flowerImageRepository.findByIdAndIsActiveTrue(id);
        if (flowerImage == null) {
            throw new CustomException("FlowerImage with ID: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return flowerImageMapper.toDto(flowerImage);
    }

    @Override
    public FlowerImage getFlowerImageEntityById(Long id) {
        return flowerImageRepository.findById(id).orElseThrow(
                () -> new CustomException("FlowerImage with ID: " + id + " not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public FlowerImageDto create(FlowerImageDto dto) {
        return flowerImageMapper.toDto(flowerImageRepository.save(flowerImageMapper.toEntity(dto)));
    }

    @Override
    public FlowerImageDto update(FlowerImageDto dto) {
        this.getById(dto.getId());
        return flowerImageMapper.toDto(flowerImageRepository.save(flowerImageMapper.toEntity(dto)));
    }

    @Override
    public void toggle(Long id) {
        FlowerImage flowerImage = getFlowerImageEntityById(id);
        flowerImage.setIsActive(!flowerImage.getIsActive());
        flowerImageRepository.save(flowerImage);
    }

    @Override
    public void delete(Long id) {
        this.getFlowerImageEntityById(id);
        flowerImageRepository.deleteById(id);
    }

    @Override
    public List<FlowerImageDto> getActiveFlowerImagesByFlowerId(Long flowerId) {
        return flowerImageRepository.findAllByFlowerIdAndIsActiveTrue(flowerId)
                .stream().map(flowerImageMapper::toDto)
                .collect(Collectors.toList());
    }
}
