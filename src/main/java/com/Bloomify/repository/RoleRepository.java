package com.Bloomify.repository;

import com.Bloomify.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByIsActiveTrue();

    Role findByIdAndIsActiveTrue(Long id);

    Set<Role> findAllByNameInAndIsActiveTrue(List<String> roleNames);

    Optional<Role> findByNameAndIsActiveTrue(String roleName);

}
