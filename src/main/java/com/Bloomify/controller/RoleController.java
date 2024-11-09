package com.Bloomify.controller;

import com.Bloomify.dto.RoleDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all roles", operationId = "getAllRolesAdmin")
    @GetMapping("/admin")
    public ResponseEntity<CustomApiResponse> getAll() {
        return CustomApiResponse.builder().data(roleService.getAll()).build();
    }

    @Operation(summary = "Get all active roles", operationId = "getAllActiveRoles")
    @GetMapping
    public ResponseEntity<CustomApiResponse> getAllActive() {
        return CustomApiResponse.builder().data(roleService.getAllActive()).build();
    }

    @Operation(summary = "Get roles for select", operationId = "getRolesForSelect")
    @GetMapping("/for-select")
    public ResponseEntity<CustomApiResponse> getForSelect() {
        return CustomApiResponse.builder().data(roleService.getForSelect()).build();
    }

    @Operation(summary = "Get active role by ID", operationId = "getActiveRoleById")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getActiveById(@PathVariable Long id) {
        return CustomApiResponse.builder().data(roleService.getActiveById(id)).build();
    }

    @Operation(summary = "Get role by ID (Admin)", operationId = "getRoleByIdAdmin")
    @GetMapping("/admin/{id}")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable Long id) {
        return CustomApiResponse.builder().data(roleService.getById(id)).build();
    }

    @Operation(summary = "Create a new role", operationId = "createRole")
    @PostMapping
    public ResponseEntity<CustomApiResponse> create(@RequestBody RoleDto dto) {
        return CustomApiResponse.builder().data(roleService.create(dto)).build();
    }

    @Operation(summary = "Update an existing role", operationId = "updateRole")
    @PutMapping
    public ResponseEntity<CustomApiResponse> update(@RequestBody RoleDto dto) {
        return CustomApiResponse.builder().data(roleService.update(dto)).build();
    }

    @Operation(summary = "Toggle role status", operationId = "toggleRole")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> toggle(@PathVariable Long id) {
        roleService.toggle(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a role", operationId = "deleteRole")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
