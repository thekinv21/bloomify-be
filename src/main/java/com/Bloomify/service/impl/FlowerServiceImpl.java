package com.Bloomify.service.impl;

import com.Bloomify.dto.FlowerDto;
import com.Bloomify.exception.CustomException;
import com.Bloomify.mapper.FlowerMapper;
import com.Bloomify.model.Flower;
import com.Bloomify.repository.FlowerRepository;
import com.Bloomify.service.FlowerService;
import com.Bloomify.spesification.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {

    private final FlowerMapper flowerMapper;
    private final FlowerRepository flowerRepository;

    @Override
    public Page<FlowerDto> getAll(String searchTerm, Pageable pageable) {
        Specification<Flower> spec =
                Specification.where(
                        new GenericSpecification<Flower>().searchBy(List.of("title", "description", "width", "height"), searchTerm));
        return flowerRepository.findAll(spec, pageable).map(flowerMapper::toDto);
    }

    @Override
    public Page<FlowerDto> getAllActive(String searchTerm, Pageable pageable) {
        Specification<Flower> spec = Specification
                .where(new GenericSpecification<Flower>()
                        .searchBy(List.of("title", "description", "width", "height"), searchTerm))
                .and((root, query, criteriaBuilder) ->
                        criteriaBuilder.isTrue(root.get("isActive"))
                );

        return flowerRepository.findAll(spec, pageable).map(flowerMapper::toDto);
    }


    @Override
    public FlowerDto getById(Long id) {
        return flowerMapper.toDto(flowerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Role with ID: " + id + " not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public FlowerDto getActiveById(Long id) {
        Flower flower = flowerRepository.findByIdAndIsActiveTrue(id);
        if (flower == null) {
            throw new CustomException("Flower with ID: " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return flowerMapper.toDto(flower);
    }

    @Override
    public Flower getFlowerEntityById(Long id) {
        return flowerRepository.findById(id).orElseThrow(
                () -> new CustomException("Flower with ID: " + id + " not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public FlowerDto create(FlowerDto dto) {
        Optional<Flower> isExistFlower = flowerRepository.findByTitleAndIsActiveTrue(dto.getTitle());
        if (isExistFlower.isPresent()) {
            throw new CustomException("Flower with title: " + dto.getTitle() + " already exists", HttpStatus.CONFLICT);
        }
        Flower flower = flowerMapper.toEntity(dto);
        flower.getFlowerImages().forEach(flowerImage -> flowerImage.setFlower(flower));
        return flowerMapper.toDto(flowerRepository.save(flower));
    }

    @Override
    public FlowerDto update(FlowerDto dto) {
        this.getById(dto.getId());
        return flowerMapper.toDto(flowerRepository.save(flowerMapper.toEntity(dto)));
    }

    @Override
    public void toggle(Long id) {
        Flower flower = getFlowerEntityById(id);
        flower.setIsActive(!flower.getIsActive());
        flowerRepository.save(flower);
    }

    @Override
    public void delete(Long id) {
        this.getFlowerEntityById(id);
        flowerRepository.deleteById(id);
    }
}
