package com.store.managementapplication.services;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

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
            storeRepository.deleteById(id);
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
