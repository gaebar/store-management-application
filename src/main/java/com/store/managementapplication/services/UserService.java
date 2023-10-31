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

    // Create a new User
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing User
    public User updateUser(Long id, User user) throws ResourceNotFoundException {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    // Delete a User by its ID
    public void deleteUser(Long id) throws ResourceNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    // Get a User by its ID
    public User getUserById(Long id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Additional functionalities
    public List<Item> viewInventory(Long storeId) {
        return itemRepository.findAllByStoreId(storeId);
    }

    public Item requestItemAddition(Long storeId, Item item) {
        item.setStoreId(storeId);
        return itemRepository.save(item);
    }

    public Item updateItemQuantity(Long storeId, Long itemId, int quantity) throws Exception {
        Optional<Item> existingItem = itemRepository.findById(itemId);

        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            item.setInitialQuantity(quantity);
            return itemRepository.save(item);
        } else {
            throw new Exception("Item not found in the given store");
        }
    }
}
