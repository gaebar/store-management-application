package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.ItemRepository;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Method to update an existing user
    public User updateUser(Long id, User user) throws ResourceNotFoundException {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    // Method to delete a user
    public void deleteUser(Long id) throws ResourceNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    // Method to view inventory for a specific store (Store Staff role)
    public List<Item> viewInventory(Long storeId) {
        return itemRepository.findAllByStoreId(storeId);
    }

    // Method to request item additions for a specific store (Store Staff role)
    public Item requestItemAddition(Long storeId, Item item) {
        item.setStoreId(storeId);  // Set the storeId for the new item
        return itemRepository.save(item);  // Save the new item
    }

    // Method to update item quantities for a specific store (Store Staff role)
    public Item updateItemQuantity(Long storeId, Long itemId, int quantity) throws Exception {
        Optional<Item> existingItem = itemRepository.findById(itemId);

        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            item.setInitialQuantity(quantity);  // Update the initialQuantity field
            return itemRepository.save(item);
        } else {
            throw new Exception("Item not found in the given store");
        }
    }
}
