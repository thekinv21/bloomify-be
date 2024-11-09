package com.Bloomify.controller;


import com.Bloomify.dto.RoleDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@Tag(name = "Role")

public class RoleController {

    private final RoleService roleService;

    @GetMapping("/admin")
    public ResponseEntity<CustomApiResponse> getAll() {
        return CustomApiResponse.builder().data(roleService.getAll()).build();
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse> getAllActive() {
        return CustomApiResponse.builder().data(roleService.getAllActive()).build();
    }

    @GetMapping("/for-select")
    public ResponseEntity<CustomApiResponse> getForSelect() {
        return CustomApiResponse.builder().data(roleService.getForSelect()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getActiveById(@PathVariable Long id) {
        return CustomApiResponse.builder().data(roleService.getActiveById(id)).build();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable Long id) {
        return CustomApiResponse.builder().data(roleService.getById(id)).build();
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse> create(@RequestBody RoleDto dto) {
        return CustomApiResponse.builder().data(roleService.create(dto)).build();
    }


    @PutMapping
    public ResponseEntity<CustomApiResponse> update(@RequestBody RoleDto dto) {
        return CustomApiResponse.builder().data(roleService.update(dto)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> toggle(@PathVariable Long id) {
        roleService.toggle(id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
