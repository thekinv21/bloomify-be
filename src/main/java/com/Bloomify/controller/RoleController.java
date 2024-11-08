package com.Bloomify.controller;


import com.Bloomify.dto.RoleDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/role")
@RequiredArgsConstructor

public class RoleController {

    private final RoleService roleService;


    @GetMapping
    public ResponseEntity<CustomApiResponse> getAll() {
        return CustomApiResponse.builder().data(roleService.getAll()).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse> getById(@PathVariable Long id) {
        return CustomApiResponse.builder().data(roleService.getById(id)).build();
    }


    @GetMapping("/roleName/{roleName}")
    public ResponseEntity<CustomApiResponse> getByRoleName(@PathVariable String roleName) {
        return CustomApiResponse.builder().data(roleService.getByRoleName(roleName)).build();
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
    public void toggle(@PathVariable Long id) {
        roleService.toggle(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

}
