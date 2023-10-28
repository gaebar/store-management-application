package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Method to get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Method to create a new item
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Method to update an existing item
    public Item updateItem(Long id, Item item) throws Exception {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return itemRepository.save(item);
        } else {
            throw new Exception("Item not found");
        }
    }

    // Method to delete an item
    public void deleteItem(Long id) throws Exception {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new Exception("Item not found");
        }
    }

    // Method to search items by name
    public List<Item> searchByName(String name) {
        // Implement search logic here
        return null; // Placeholder until search logic is implemented
    }

    // Method to search items by category
    public List<Item> searchByCategory(String category) {
        // Implement search logic here
        return null; // Placeholder until search logic is implemented
    }

    // Method to search items by price range
    public List<Item> searchByPriceRange(double minPrice, double maxPrice) {
        // Implement search logic here
        return null; // Placeholder until search logic is implemented
    }
}
