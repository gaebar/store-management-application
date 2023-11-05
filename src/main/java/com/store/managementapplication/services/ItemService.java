package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.ItemCategory;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.CategoryRepository;
import com.store.managementapplication.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    // Get item by ID
    public Item getItemById(Long id) throws ResourceNotFoundException {
        return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }


    // Create a new item
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // add category to item
    public Item addCategoryToItem(Long itemId, Long categoryId) throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        ItemCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.addItem(item);
        categoryRepository.save(category);
        return itemRepository.save(item);
    }

    // Update an existing item
    public Item updateItem(Long id, Item item) throws ResourceNotFoundException {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return itemRepository.save(item);
        } else {
            throw new ResourceNotFoundException("Item not found");
        }
    }

    // Delete an existing item
    public void deleteItem(Long id) throws ResourceNotFoundException {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found");
        }
    }

    // Add item to category
    public Item addItemToCategory(Long itemId, Long categoryId) throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        ItemCategory category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.addItem(item);
        categoryRepository.save(category);
        return itemRepository.save(item);
    }

    // Get category by item ID
    public ItemCategory getCategoryByItemId(Long itemId) throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        return item.getCategory();
    }

//    // Logic for Store Staff to view inventory by store
//    public List<Item> viewInventoryByStore(Long storeId) {
//        return itemRepository.findAllByStoreId(storeId);
//    }


//    // Logic for Store Staff to update item status
//    public Item updateItemStatus(Long itemId, String newStatus) throws ResourceNotFoundException {
//        Optional<Item> optionalItem = itemRepository.findById(itemId);
//        if (optionalItem.isPresent()) {
//            Item item = optionalItem.get();
//            item.setStatus(newStatus);
//            return itemRepository.save(item);
//        } else {
//            throw new ResourceNotFoundException("Item not found");
//        }
//    }

    // Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Additional functionalities like search can go here
    public List<Item> searchByName(String name) {
        return itemRepository.findByNameContaining(name);
    }

    public List<Item> searchByCategory(String category) {
        return itemRepository.findByCategoryContaining(category);
    }

    public List<Item> searchByPriceRange(double minPrice, double maxPrice) {
        return itemRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
