package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom query methods here
}