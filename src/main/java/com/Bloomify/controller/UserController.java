package com.Bloomify.controller;


import com.Bloomify.dto.UserDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController  {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<CustomApiResponse> getAll() {
        return CustomApiResponse.builder().data(userService.getAll()).build();
    }


    @GetMapping("/{id}")
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
    public void toggle(@PathVariable UUID id) {
        userService.toggle(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

}
