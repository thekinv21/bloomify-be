package com.Bloomify.service.impl;

import com.Bloomify.mapper.BaseMapper;
import com.Bloomify.repository.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<MODEL,DTO,ID> {

    private final BaseRepository<MODEL, ID> repository;
    private final BaseMapper<MODEL, DTO> mapper;

    protected BaseServiceImpl(BaseRepository<MODEL, ID> repository, BaseMapper<MODEL, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<DTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public DTO getById(ID id) {
        return mapper.toDto(getModelById(id));
    }


    public MODEL getModelById(ID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(id.toString()));
    }


    public Optional<MODEL> getOptModelById(ID id) {
        return repository.findById(id);
    }


    public Optional<DTO> getOptDtoById(ID id) {
        return repository.findById(id).map(mapper::toDto);
    }


    public DTO create(DTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public DTO update(ID id, DTO dto) {
        var existing = getModelById(id);
        mapper.partialUpdate(dto, existing);
        return mapper.toDto(repository.save(existing));
    }


    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }
}
