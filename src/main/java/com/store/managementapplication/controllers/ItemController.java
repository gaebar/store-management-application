package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.ItemCategory;
import com.store.managementapplication.services.CategoryService;
import com.store.managementapplication.services.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

}
