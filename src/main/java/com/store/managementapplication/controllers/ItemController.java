package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create a new item
    @PostMapping("/create")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    // Update an existing item
    @PutMapping("/update/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    // Delete an item
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    // Search for items based on item name
    @GetMapping("/search/name/{name}")
    public List<Item> searchByName(@PathVariable String name) {
        return itemService.searchByName(name);
    }

    // Search for items based on category
    @GetMapping("/search/category/{category}")
    public List<Item> searchByCategory(@PathVariable String category) {
        return itemService.searchByCategory(category);
    }

    // Search for items based on price range
    @GetMapping("/search/price/{minPrice}/{maxPrice}")
    public List<Item> searchByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return itemService.searchByPriceRange(minPrice, maxPrice);
    }
}
