package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.ItemCategory;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ItemCategory createCategory(@RequestBody ItemCategory itemCategory) {
        return categoryService.createCategory(itemCategory);
    }

    @PutMapping("/update/{categoryId}")
    public ItemCategory updateCategory(@PathVariable Long categoryId, @RequestBody ItemCategory itemCategory) throws ResourceNotFoundException {
        return categoryService.updateCategory(categoryId, itemCategory);
    }

    @GetMapping("/{categoryId}")
    public ItemCategory getCategory(@PathVariable Long categoryId) throws ResourceNotFoundException {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) throws ResourceNotFoundException {
        categoryService.deleteCategory(categoryId);
    }
}
