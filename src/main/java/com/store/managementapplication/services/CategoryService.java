package com.store.managementapplication.services;

import com.store.managementapplication.entities.ItemCategory;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    // Create a new itemCategory
    public ItemCategory createCategory(ItemCategory itemCategory) {
        return categoryRepository.save(itemCategory);
    }

    // Update an existing category
    public ItemCategory updateCategory(Long categoryId, ItemCategory updatedItemCategory) throws ResourceNotFoundException {
        if (categoryRepository.existsById(categoryId)) {
            updatedItemCategory.setId(categoryId);
            return categoryRepository.save(updatedItemCategory);
        } else {
            throw new ResourceNotFoundException("ItemCategory not found");
        }
    }

    // Delete an existing category
    public void deleteCategory(Long categoryId) throws ResourceNotFoundException {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new ResourceNotFoundException("ItemCategory not found");
        }
    }

    public ItemCategory getCategoryById(Long categoryId) throws ResourceNotFoundException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemCategory not found for this id :: " + categoryId));
    }

    // get all categories
    public List<ItemCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}
