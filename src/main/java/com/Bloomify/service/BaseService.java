package com.Bloomify.service;


import java.util.List;
import java.util.Optional;

public interface BaseService<MODEL,DTO,ID> {


    List<DTO> getAll();

    DTO getById(ID id);

    MODEL getModelById(ID id);

    Optional<MODEL> getOptModelById(ID id);

    Optional<DTO> getOptDtoById(ID id);

    DTO create(DTO dto);

    DTO update(ID id, DTO dto);

    void delete(ID id);
}
