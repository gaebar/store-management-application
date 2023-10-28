package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // Add custom query methods here
}
