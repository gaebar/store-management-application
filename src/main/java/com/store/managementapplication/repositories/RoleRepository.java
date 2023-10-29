package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository interface for Role entity.
 *
 * This interface inherits from JpaRepository, which provides methods
 * to perform CRUD operations (Create, Read, Update, Delete) as well as
 * pagination and sorting. By extending JpaRepository, this interface
 * will have methods like findAll(), save(), delete(), etc., automatically
 * implemented at runtime by Spring Data JPA.
 *
 * The first generic parameter "Role" indicates the domain type the repository manages,
 * and the second generic parameter "Long" indicates the type of the id of the entity.
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
}
