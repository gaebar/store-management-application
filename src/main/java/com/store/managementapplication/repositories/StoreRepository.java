package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * StoreRepository extends JpaRepository to gain CRUD operations.
 * "Store" is the entity type and "Long" is the ID type.
 */
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByStoreTypeContaining(String storeType);

    List<Store> findByLocationContainingIgnoreCase(String location);

    List<Store> findByOpeningDateContaining(String date);


}
