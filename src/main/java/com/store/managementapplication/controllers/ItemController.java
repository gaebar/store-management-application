package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Only admins and store managers can create a new item
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @PostMapping("/create")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    // Only admins and store managers can update an existing item
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @PutMapping("/update/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    // Only admins and store managers can delete an item
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    // Any authenticated user can search for items by name
    @GetMapping("/search/name/{name}")
    public List<Item> searchByName(@PathVariable String name) {
        return itemService.searchByName(name);
    }

    // Any authenticated user can search for items by category
    @GetMapping("/search/category/{category}")
    public List<Item> searchByCategory(@PathVariable String category) {
        return itemService.searchByCategory(category);
    }

    // Any authenticated user can search for items by price range
    @GetMapping("/search/price/{minPrice}/{maxPrice}")
    public List<Item> searchByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return itemService.searchByPriceRange(minPrice, maxPrice);
    }
}
