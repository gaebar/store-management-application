package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * ItemRepository extends JpaRepository to gain CRUD operations.
 * "Item" is the entity type and "Long" is the ID type.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContaining(String name);

    List<Item> findByCategoryContaining(String category);

    List<Item> findByPriceBetween(double minPrice, double maxPrice);


    List<Item> findAllByStoreId(Long storeId);

}
