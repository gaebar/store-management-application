package com.store.managementapplication.services;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.entities.StoreInventory;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.StoreInventoryRepository;
import com.store.managementapplication.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreInventoryRepository storeInventoryRepository;

    public StoreService(StoreRepository storeRepository, StoreInventoryRepository storeInventoryRepository) {
        this.storeRepository = storeRepository;
        this.storeInventoryRepository = storeInventoryRepository;
    }

    // Create a new Store
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    // Update an existing Store
    public Store updateStore(Long id, Store store) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            store.setId(id);
            return storeRepository.save(store);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Delete a Store by its ID
    public void deleteStore(Long id) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            // Retrieve all store inventories related to the store
            List<StoreInventory> inventories = storeInventoryRepository.findAllByStoreId(id);
            // Delete each store inventory entry
            for (StoreInventory inventory : inventories) {
                storeInventoryRepository.deleteById(inventory.getId());
            }
            // Delete the store itself
            storeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Get a Store by its ID
    public Store getStoreById(Long id) throws ResourceNotFoundException {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    // Get store inventory by store ID
    public List<StoreInventory> getStoreInventory(Long id) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            return storeInventoryRepository.findAllByStoreId(id);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Get all Stores
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Additional functionalities
    public List<Store> searchByLocation(String location) {
        return storeRepository.findByLocationContaining(location);
    }

    public List<Store> searchByType(String storeType) {
        return storeRepository.findByStoreTypeContaining(storeType);
    }

    public List<Store> searchByOpeningDate(String date) {
        return storeRepository.findByOpeningDateContaining(date);
    }
}
