package com.Bloomify.repository;

import com.Bloomify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsernameAndIsActiveTrue(String username);

    Optional<User> findByEmailAndIsActiveTrue(String email);

    User findByUsername(String username);
    User findByEmail(String email);

    List<User> findAllByIsActiveTrue();

    Optional<User> findByIdAndIsActiveTrue(UUID id);

}
