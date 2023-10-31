package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Category;
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
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/update/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId, @RequestBody Category category) throws ResourceNotFoundException {
        return categoryService.updateCategory(categoryId, category);
    }

    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) throws ResourceNotFoundException {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) throws ResourceNotFoundException {
        categoryService.deleteCategory(categoryId);
    }
}
