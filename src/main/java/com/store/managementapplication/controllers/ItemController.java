package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.ItemCategory;
import com.store.managementapplication.services.CategoryService;
import com.store.managementapplication.services.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@Tag(name = "Item", description = "Item API")
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    // Only admins and store managers can create a new item
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    // add category to item
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/addCategory/{itemId}/{categoryId}")
    public Item addCategoryToItem(@PathVariable Long itemId, @PathVariable Long categoryId) {
        return itemService.addCategoryToItem(itemId, categoryId);
    }

    // Only admins can update an existing items
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    // Only admins can delete an item
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    // Only admins can add an item to a category
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @PutMapping("/addToCategory/{itemId}/{categoryId}")
    public Item addItemToCategory(@PathVariable Long itemId, @PathVariable Long categoryId) {
        Item item = itemService.addItemToCategory(itemId, categoryId);
        ItemCategory category = categoryService.getCategoryById(categoryId);
        item.setCategory(
                new ItemCategory(
                        category.getId(),
                        category.getName(),
                        category.getSupplier()
                )
        );

        return item;
    }

    // Get item by ID
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item.getCategory() == null) {
            return item;
        }
        
        item.setCategory(
                new ItemCategory(
                        item.getCategory().getId(),
                        item.getCategory().getName(),
                        item.getCategory().getSupplier()
                )
        );
        return item;
    }


    /*

    // Store Staff can view store inventory
    @PreAuthorize("hasRole('STORE_STAFF')")
    @GetMapping("/viewInventory/{storeId}")
    public List<Item> viewInventoryByStore(@PathVariable Long storeId) {
        return itemService.viewInventoryByStore(storeId);
    }

    // Store Staff can request item additions
    @PreAuthorize("hasRole('STORE_STAFF')")
    @PostMapping("/requestAddition")
    public Item requestItemAddition(@RequestBody Item item) {
        return itemService.requestItemAddition(item);
    }

    // Store Staff can update item statuses
    @PreAuthorize("hasPermission('item:status_update')")
    @PutMapping("/updateStatus/{itemId}/{newStatus}")
    public Item updateItemStatus(@PathVariable Long itemId, @PathVariable String newStatus) {
        return new Item(); //itemService.updateItemStatus(itemId, newStatus);
    }

    */

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
