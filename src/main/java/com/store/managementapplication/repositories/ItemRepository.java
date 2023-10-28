package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // Add custom query methods here, if needed
}
