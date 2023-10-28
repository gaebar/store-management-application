package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContaining(String name);
}
