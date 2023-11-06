package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.ItemCategory;
import com.store.managementapplication.entities.Store;
import com.store.managementapplication.services.CategoryService;
import com.store.managementapplication.services.ItemService;
import com.store.managementapplication.services.StoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@Tag(name = "Search", description = "Search API")
public class ItemSearchController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final StoreService storeService;

    public ItemSearchController(ItemService itemService, CategoryService categoryService, StoreService storeService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.storeService = storeService;
    }


    // Any authenticated user can search for items by category
    @GetMapping("/category/{category}")
    public List<Item> searchByCategory(@PathVariable String category) {
        return itemService.searchByCategory(category);
    }

    // Any authenticated user can search for items by price range
    @GetMapping("/price/{minPrice}/{maxPrice}")
    public List<Item> searchByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        var items = itemService.searchByPriceRange(minPrice, maxPrice);
        items.forEach(item -> item.setCategory(
                new ItemCategory(item.getCategory().getId(), item.getCategory().getName(), item.getCategory().getSupplier())));

        return items;
    }

    // Search item by name
    @GetMapping("/item-name/{name}")
    public List<Item> searchByName(@PathVariable String name) {
        var items = itemService.searchByName(name);
        items.forEach(item -> item.setCategory(
                new ItemCategory(item.getCategory().getId(), item.getCategory().getName(), item.getCategory().getSupplier())));

        return items;
    }

    // Search item by name and category
    @GetMapping("/item-name/{name}/category-name/{category}")
    public List<Item> searchByNameAndCategory(@PathVariable String name, @PathVariable String category) {
        var items = itemService.searchByNameContainingAndCategoryNameContaining(name, category);
        items.forEach(item -> item.setCategory(
                new ItemCategory(item.getCategory().getId(), item.getCategory().getName(), item.getCategory().getSupplier())));

        return items;
    }

    // Search Stores by location
    @GetMapping("/store-location/{location}")
    public List<Store> searchByLocation(@PathVariable String location) {
        var stores = storeService.searchByLocation(location);
        return stores;
    }
}
