package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ItemRepository extends JpaRepository to gain CRUD operations.
 * "Item" is the entity type and "Long" is the ID type.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContainingIgnoreCase(String name);

    List<Item> findByCategoryContaining(String category);

    List<Item> findByPriceBetween(double minPrice, double maxPrice);

    List<Item> findByPriceLessThan(double price);

    List<Item> findByPriceGreaterThan(double price);

    @Query("SELECT i FROM Item i JOIN i.category c WHERE UPPER(i.name) LIKE UPPER(?1) AND UPPER(c.name) LIKE UPPER(?2)")
    List<Item> findByNameContainingIgnoreCaseAndCategoryNameContainingIgnoreCase(String name, String category);

    List<Item> findByNameContainingAndCategoryContaining(String name, String category);
}
