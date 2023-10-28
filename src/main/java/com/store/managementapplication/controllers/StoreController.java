package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController // Indicates that the class is a RESTful web service controller
@RequestMapping("/stores") // Maps all HTTP operations by default
public class StoreController {

    @Autowired // Automatically injects StoreService into this controller
    private StoreService storeService;

    @GetMapping("/") // Maps to HTTP GET and fetches all stores
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @PreAuthorize("hasRole('ADMIN')") // Ensures only users with the role ADMIN can access this endpoint
    @PostMapping("/createStore") // Maps to HTTP POST and creates a new store
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }

    // Update an existing store
    @PutMapping("/updateStore/{id}") // Maps to HTTP PUT and updates an existing store
    public Store updateStore(@PathVariable Long id, @RequestBody Store store) {
        return storeService.updateStore(id, store);
    }

    // Delete a store
    @DeleteMapping("/deleteStore/{id}") // Maps to HTTP DELETE and removes a store
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }

    // Search for stores based on location
    @GetMapping("/search/location/{location}") // Maps to HTTP GET and fetches stores by location
    public List<Store> searchByLocation(@PathVariable String location) {
        return storeService.searchByLocation(location);
    }

    // Search for stores based on type
    @GetMapping("/search/type/{type}") // Maps to HTTP GET and fetches stores by type
    public List<Store> searchByType(@PathVariable String type) {
        return storeService.searchByType(type);
    }

    // Search for stores based on opening date
    @GetMapping("/search/openingDate/{date}") // Maps to HTTP GET and fetches stores by opening date
    public List<Store> searchByOpeningDate(@PathVariable String date) {
        return storeService.searchByOpeningDate(date);
    }
}
