package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByStoreTypeContaining(String storeType);  // Renamed method
    List<Store> findByLocationContaining(String location);
    List<Store> findByOpeningDateContaining(String date);
}
