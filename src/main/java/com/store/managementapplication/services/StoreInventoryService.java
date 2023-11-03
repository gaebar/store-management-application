package com.store.managementapplication.services;

import com.store.managementapplication.entities.StoreInventory;
import com.store.managementapplication.repositories.StoreInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreInventoryService {


    private final StoreInventoryRepository storeInventoryRepository;

    public StoreInventoryService(StoreInventoryRepository storeInventoryRepository) {
        this.storeInventoryRepository = storeInventoryRepository;
    }

    public List<StoreInventory> getAllInventories() {
        return storeInventoryRepository.findAll();
    }

    public Optional<StoreInventory> getInventoryById(Long id) {
        return storeInventoryRepository.findById(id);
    }

    public StoreInventory saveInventory(StoreInventory storeInventory) {
        return storeInventoryRepository.save(storeInventory);
    }

    public void deleteInventory(Long id) {
        storeInventoryRepository.deleteById(id);
    }

    public StoreInventory addItemToStoreInventory(Long storeId, Long itemId, int count) {
        StoreInventory inventory = getInventoryByItemIdAndStoreId(itemId, storeId);
        if (inventory == null) {
            inventory = new StoreInventory(storeId, itemId, count);
        } else {
            inventory.addItems(count);
        }

        return saveInventory(inventory);
    }

    public StoreInventory getInventoryByItemIdAndStoreId(Long itemId, Long storeId) {
        // if exists, return the inventory
        // else, create a new inventory and return it
        return storeInventoryRepository.findByItemIdAndStoreId(itemId, storeId);
    }

    public boolean isInventoryBelowThreshold(Long id, int threshold) {
        StoreInventory inventory = getInventoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory ID"));
        return inventory.isBelowThreshold(threshold);
    }
}
