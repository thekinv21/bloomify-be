package com.Bloomify.controller;

import com.Bloomify.dto.FlowerDto;
import com.Bloomify.response.CustomApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flower")
@RequiredArgsConstructor
@Tag(name = "Flower")
public class FlowerController {


    @Operation(summary = "Get all Flowers (Admin)", operationId = "getAllFlowersAdmin")
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> getAll(
            @RequestParam(required = false) String searchTerm,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return null;
    }

    @Operation(summary = "Get all active Flowers", operationId = "getAllActiveFlowers")
    @GetMapping
    public ResponseEntity<CustomApiResponse> getAllActive() {
        return null;
    }


    @Operation(summary = "Get active Flower by ID", operationId = "getActiveFlowerById")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getActiveById(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "Get Flower by ID (Admin)", operationId = "getFlowerByIdAdmin")
    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "Create a new Flower", operationId = "createFlower")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> create(@RequestBody FlowerDto dto) {
        return null;
    }

    @Operation(summary = "Update an existing Flower", operationId = "updateFlower")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<CustomApiResponse> update(@RequestBody FlowerDto dto) {
        return null;
    }

    @Operation(summary = "Toggle Flower status", operationId = "toggleFlower")
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<Void> toggle(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "Delete a Flower", operationId = "deleteFlower")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
      return null;
    }
}
