package com.Bloomify.controller;


import com.Bloomify.dto.UserDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User")

public class UserController  {

    private final UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<CustomApiResponse> getAll() {
        return CustomApiResponse.builder().data(userService.getAll()).build();
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse> getAllActive() {
        return CustomApiResponse.builder().data(userService.getAllActive()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getActiveById(@PathVariable UUID id) {
        return CustomApiResponse.builder().data(userService.getActiveById(id)).build();
    }

    @GetMapping("/for-select")
    public ResponseEntity<CustomApiResponse> getForSelect() {
        return CustomApiResponse.builder().data(userService.getForSelect()).build();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable UUID id) {
        return CustomApiResponse.builder().data(userService.getById(id)).build();
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<CustomApiResponse> getByEmail(@PathVariable String email) {
        return CustomApiResponse.builder().data(userService.getByEmail(email)).build();
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<CustomApiResponse> getByUsername(@PathVariable String username) {
        return CustomApiResponse.builder().data(userService.getByUsername(username)).build();
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse> create(@RequestBody UserDto dto) {
        return CustomApiResponse.builder().data(userService.create(dto)).build();
    }


    @PutMapping
    public ResponseEntity<CustomApiResponse> update(@RequestBody UserDto dto) {
        return CustomApiResponse.builder().data(userService.update(dto)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> toggle(@PathVariable UUID id) {
        userService.toggle(id);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
