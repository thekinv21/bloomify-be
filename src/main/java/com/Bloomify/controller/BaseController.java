package com.Bloomify.controller;

import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class BaseController<MODEL,DTO,ID> {

    private final BaseService<MODEL, DTO, ID> service;

    protected BaseController(BaseService<MODEL, DTO, ID> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse> getAll(){
        List<DTO> response = service.getAll();
        return CustomApiResponse.builder().data(response).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable ID id) {
        Optional<DTO> dto = service.getOptDtoById(id);
        return CustomApiResponse.builder().data(dto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build())).build();
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse> create(@Valid @RequestBody DTO dto) {
        DTO createdDto = service.create(dto);
        return CustomApiResponse.builder().data(createdDto).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse> update(@PathVariable ID id, @Valid @RequestBody DTO dto) {
        DTO updatedDto = service.update(id, dto);
        return CustomApiResponse.builder().data(updatedDto).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
