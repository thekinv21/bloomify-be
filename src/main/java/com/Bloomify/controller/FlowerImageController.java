package com.Bloomify.controller;

import com.Bloomify.dto.FlowerImageDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.FlowerImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flower-image")
@RequiredArgsConstructor
@Tag(name = "Flower-Images")
public class FlowerImageController {

    private final FlowerImageService flowerImageService;

    @Operation(summary = "Get all Flowers Images (Admin)", operationId = "getAllFlowerImagesAdmin")
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> getAll(
            @RequestParam(required = false) String searchTerm,
            @PageableDefault() Pageable pageable
    ) {
        return CustomApiResponse.builder()
                .pageableData(flowerImageService.getAll(searchTerm, pageable))
                .build();
    }

    @Operation(summary = "Get all active Flower Images", operationId = "getAllActiveFlowerImages")
    @GetMapping
    public ResponseEntity<CustomApiResponse> getAllActive(
            @RequestParam(required = false) String searchTerm,
            @PageableDefault() Pageable pageable
    ) {
        return CustomApiResponse.builder()
                .pageableData(flowerImageService.getAllActive(searchTerm, pageable))
                .build();
    }


    @Operation(summary = "Get active Flower Images by ID", operationId = "getActiveFlowerById")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getActiveById(@PathVariable Long id) {
        return CustomApiResponse.builder().data(flowerImageService.getActiveById(id)).build();
    }

    @Operation(summary = "Get Flower Image by ID (Admin)", operationId = "getFlowerImageByIdAdmin")
    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable Long id) {
        return CustomApiResponse.builder().data(flowerImageService.getById(id)).build();
    }

    @Operation(summary = "Create a new Flower Image", operationId = "createFlowerImage")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> create(@RequestBody FlowerImageDto dto) {
        return CustomApiResponse.builder().data(flowerImageService.create(dto)).build();
    }

    @Operation(summary = "Update an existing Flower Image", operationId = "updateFlowerImage")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> update(@RequestBody FlowerImageDto dto) {
        return CustomApiResponse.builder().data(flowerImageService.update(dto)).build();
    }

    @Operation(summary = "Toggle Flower Image status", operationId = "toggleFlowerImage")
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<Void> toggle(@PathVariable Long id) {
        flowerImageService.toggle(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a Flower Image", operationId = "deleteFlowerImage")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        flowerImageService.delete(id);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Get active Flower Images by Flower ID", operationId = "getActiveFlowerImagesByFlowerId")
    @GetMapping("/flower/{flowerId}")
    public ResponseEntity<CustomApiResponse> getActiveFlowerImagesByFlowerId(@PathVariable Long flowerId) {
        return CustomApiResponse.builder().data(flowerImageService.getActiveFlowerImagesByFlowerId(flowerId)).build();
    }
}
