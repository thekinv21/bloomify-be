package com.Bloomify.repository;

import com.Bloomify.model.Flower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface FlowerRepository extends JpaRepository<Flower, Long> {
    Flower findByIdAndIsActiveTrue(Long id);
    Page<Flower> findAll(Specification<Flower> spec, Pageable pageable);
    Optional<Flower> findByTitleAndIsActiveTrue(String title);
}
