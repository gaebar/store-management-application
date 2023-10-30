package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Create a new item
    public Item createItem(Item item) {
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
