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

    public void addItemsToInventory(Long id, int count) {
        StoreInventory inventory = getInventoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory ID"));
        inventory.addItems(count);
        storeInventoryRepository.save(inventory);
    }

    public void removeItemsFromInventory(Long id, int count) {
        StoreInventory inventory = getInventoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory ID"));
        inventory.removeItems(count);
        storeInventoryRepository.save(inventory);
    }

    public boolean isInventoryBelowThreshold(Long id, int threshold) {
        StoreInventory inventory = getInventoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory ID"));
        return inventory.isBelowThreshold(threshold);
    }
}
