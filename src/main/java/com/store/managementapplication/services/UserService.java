package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Existing method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Method to update an existing user
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    // Method to delete a user
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    // Method to view inventory for a specific store (Store Staff role)
    public List<Item> viewInventory(Long storeId) {
        // Implement logic here
        return null;
    }

    // Method to request item additions for a specific store (Store Staff role)
    public Item requestItemAddition(Long storeId, Item item) {
        // Implement logic here
        return null;
    }

    // Method to update item quantities for a specific store (Store Staff role)
    public Item updateItemQuantity(Long storeId, Long itemId, int quantity) {
        // Implement logic here
        return null;
    }
}
