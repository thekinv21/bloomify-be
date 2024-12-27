package com.Bloomify.repository;

import com.Bloomify.model.FlowerImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FlowerImageRepository extends JpaRepository<FlowerImage, Long> {
    FlowerImage findByIdAndIsActiveTrue(Long id);
    Page<FlowerImage> findAll(Specification<FlowerImage> spec, Pageable pageable);
    List<FlowerImage> findAllByFlowerIdAndIsActiveTrue(Long flowerId);
}
