package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Get all items.
     * @return A list of all items.
     */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Create a new item.
     * @param item The item to create.
     * @return The created item.
     */
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * Update an existing item.
     * @param id The ID of the item to update.
     * @param item The new item data.
     * @return The updated item.
     * @throws ResourceNotFoundException if the item is not found.
     */
    public Item updateItem(Long id, Item item) throws ResourceNotFoundException {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            return itemRepository.save(item);
        } else {
            throw new ResourceNotFoundException("Item not found");
        }
    }

    /**
     * Delete an existing item.
     * @param id The ID of the item to delete.
     * @throws ResourceNotFoundException if the item is not found.
     */
    public void deleteItem(Long id) throws ResourceNotFoundException {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found");
        }
    }

    /**
     * Search items by name.
     * @param name The name to search for.
     * @return A list of items that match the name.
     */
    public List<Item> searchByName(String name) {
        return itemRepository.findByNameContaining(name);
    }

    /**
     * Search items by category.
     * @param category The category to search for.
     * @return A list of items that match the category.
     */
    public List<Item> searchByCategory(String category) {
        return itemRepository.findByCategoryContaining(category);
    }

    /**
     * Search items by price range.
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of items that fall within the price range.
     */
    public List<Item> searchByPriceRange(double minPrice, double maxPrice) {
        return itemRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
