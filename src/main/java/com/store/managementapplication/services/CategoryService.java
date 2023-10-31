
package com.store.managementapplication.services;

import com.store.managementapplication.entities.Category;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Update an existing category
    public Category updateCategory(Long categoryId, Category updatedCategory) throws ResourceNotFoundException {
        if (categoryRepository.existsById(categoryId)) {
            updatedCategory.setId(categoryId);
            return categoryRepository.save(updatedCategory);
        } else {
            throw new ResourceNotFoundException("Category not found");
        }
    }

    // Delete an existing category
    public void deleteCategory(Long categoryId) throws ResourceNotFoundException {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new ResourceNotFoundException("Category not found");
        }
    }
}
