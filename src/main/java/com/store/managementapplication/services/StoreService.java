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

    // Method to get all stores
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Method to create a store
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    // Method to update a store
    public Store updateStore(Long id, Store store) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            store.setId(id);
            return storeRepository.save(store);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Method to delete a store
    public void deleteStore(Long id) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Method to search stores by location
    public List<Store> searchByLocation(String location) {
        return storeRepository.findByLocationContaining(location);
    }

    // Method to search stores by type
    public List<Store> searchByType(String storeType) {
        return storeRepository.findByStoreTypeContaining(storeType);
    }

    // Method to search stores by opening date
    public List<Store> searchByOpeningDate(String date) {
        return storeRepository.findByOpeningDateContaining(date);
    }
}
