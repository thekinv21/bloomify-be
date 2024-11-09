package com.Bloomify.controller;

import com.Bloomify.dto.UserDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users (Admin)", operationId = "getAllUsersAdmin")
    @GetMapping("/admin")
    public ResponseEntity<CustomApiResponse> getAll() {
        return CustomApiResponse.builder().data(userService.getAll()).build();
    }

    @Operation(summary = "Get all active users", operationId = "getAllActiveUsers")
    @GetMapping
    public ResponseEntity<CustomApiResponse> getAllActive() {
        return CustomApiResponse.builder().data(userService.getAllActive()).build();
    }

    @Operation(summary = "Get active user by ID", operationId = "getActiveUserById")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getActiveById(@PathVariable UUID id) {
        return CustomApiResponse.builder().data(userService.getActiveById(id)).build();
    }

    @Operation(summary = "Get users for select", operationId = "getUsersForSelect")
    @GetMapping("/for-select")
    public ResponseEntity<CustomApiResponse> getForSelect() {
        return CustomApiResponse.builder().data(userService.getForSelect()).build();
    }

    @Operation(summary = "Get user by ID (Admin)", operationId = "getUserByIdAdmin")
    @GetMapping("/admin/{id}")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable UUID id) {
        return CustomApiResponse.builder().data(userService.getById(id)).build();
    }

    @Operation(summary = "Get user by email", operationId = "getUserByEmail")
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomApiResponse> getByEmail(@PathVariable String email) {
        return CustomApiResponse.builder().data(userService.getByEmail(email)).build();
    }

    @Operation(summary = "Get user by username", operationId = "getUserByUsername")
    @GetMapping("/username/{username}")
    public ResponseEntity<CustomApiResponse> getByUsername(@PathVariable String username) {
        return CustomApiResponse.builder().data(userService.getByUsername(username)).build();
    }

    @Operation(summary = "Create a new user", operationId = "createUser")
    @PostMapping
    public ResponseEntity<CustomApiResponse> create(@RequestBody UserDto dto) {
        return CustomApiResponse.builder().data(userService.create(dto)).build();
    }

    @Operation(summary = "Update an existing user", operationId = "updateUser")
    @PutMapping
    public ResponseEntity<CustomApiResponse> update(@RequestBody UserDto dto) {
        return CustomApiResponse.builder().data(userService.update(dto)).build();
    }

    @Operation(summary = "Toggle user status", operationId = "toggleUser")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> toggle(@PathVariable UUID id) {
        userService.toggle(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a user", operationId = "deleteUser")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
