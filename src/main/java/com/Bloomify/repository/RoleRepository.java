package com.Bloomify.repository;

import com.Bloomify.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByIsActiveTrue();
    Optional<Role> findByNameAndIsActiveTrue(String roleName);

}
