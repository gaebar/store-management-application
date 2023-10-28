package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createStore")
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }

    // Update an existing store
    @PutMapping("/updateStore/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store store) {
        return storeService.updateStore(id, store);
    }

    // Delete a store
    @DeleteMapping("/deleteStore/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }

    // Search for stores based on location
    @GetMapping("/search/location/{location}")
    public List<Store> searchByLocation(@PathVariable String location) {
        return storeService.searchByLocation(location);
    }

    // Search for stores based on type
    @GetMapping("/search/type/{type}")
    public List<Store> searchByType(@PathVariable String type) {
        return storeService.searchByType(type);
    }

    // Search for stores based on opening date
    @GetMapping("/search/openingDate/{date}")
    public List<Store> searchByOpeningDate(@PathVariable String date) {
        return storeService.searchByOpeningDate(date);
    }
}
