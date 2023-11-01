package com.store.managementapplication.services;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.ItemRepository;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    public UserService(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .map(existingUser -> {
                    user.setId(id);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public void deleteUser(Long id) throws ResourceNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    public List<Item> viewInventory(Long storeId) {
//        return itemRepository.findAllByStoreId(storeId);
//    }

    public Item requestItemAddition(Long storeId, Item item) {
        item.setStoreId(storeId);
        return itemRepository.save(item);
    }

    public Item updateItemQuantity(Long storeId, Long itemId, int quantity) throws Exception {
        return itemRepository.findById(itemId)
                .map(existingItem -> {
                    existingItem.setInitialQuantity(quantity);
                    return itemRepository.save(existingItem);
                })
                .orElseThrow(() -> new Exception("Item not found in the given store"));
    }

    public boolean isUserStoreManager(String email) {
        return userRepository.findByEmail(email)
                .map(user -> "STORE_MANAGER".equals(user.getRole().name()))
                .orElse(false);
    }

    public boolean isManagerOfStore(String email, Long storeId) {
        return userRepository.findByEmail(email)
                .map(user -> user.getManagedStores().stream()
                        .anyMatch(store -> store.getId().equals(storeId)))
                .orElse(false);
    }
}
