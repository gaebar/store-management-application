package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreInventoryRepository extends JpaRepository<StoreInventory, Long> {

    List<StoreInventory> findAllByStoreId(Long storeId);

    List<StoreInventory> findAllByItemId(Long itemId);

    List<StoreInventory> findAllByItemIdAndStoreId(Long itemId, Long storeId);

    // HQL query to find store inventory by item id and store id
    //  @Query(value = "SELECT * FROM STORE_INVENTORY WHERE item_id = ?1 AND store_id = ?2", nativeQuery = true)
    //  List<StoreInventory> findByItemIdAndStoreId(Long itemId, Long storeId);

}
