package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
