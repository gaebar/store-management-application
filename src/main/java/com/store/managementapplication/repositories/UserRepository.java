package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository extends JpaRepository to gain CRUD operations.
 * "User" is the entity type and "Long" is the ID type.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
