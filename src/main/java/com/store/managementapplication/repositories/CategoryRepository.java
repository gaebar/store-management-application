package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ItemCategory, Long> {
    // Basic CRUD methods are already provided by JpaRepository
}
