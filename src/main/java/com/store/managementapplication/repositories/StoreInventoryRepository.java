package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreInventoryRepository extends JpaRepository<StoreInventory, Long> {

    List<StoreInventory> findAllByStoreId(Long storeId);

}
