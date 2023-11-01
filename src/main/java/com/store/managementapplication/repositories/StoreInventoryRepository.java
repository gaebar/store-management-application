package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreInventoryRepository extends JpaRepository<StoreInventory, Long> {

    List<StoreInventory> findAllByStoreId(Long storeId);

}
