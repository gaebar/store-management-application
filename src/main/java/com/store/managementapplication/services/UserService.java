package com.store.managementapplication.services;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.StoreRepository;
import com.store.managementapplication.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public UserService(UserRepository userRepository, StoreRepository storeRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public User createUser(User user) {
        String password = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) throws ResourceNotFoundException {
        User prevUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setManagedStores(prevUser.getManagedStores());
        return userRepository.findById(id).map(existingUser -> {
            user.setId(id);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User updateUser2(Long id, User user) throws ResourceNotFoundException {
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
        //map users to stores, remove the additional store properties, keep id only
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //use map or foreach to remove the additional store properties from the User object, keep id only
        var managedStores = user.getManagedStores();
        // remove all properties except id from the managedStores
        managedStores.forEach(store -> {
            store.setStoreInventories(null);
        });
        user.setManagedStores(managedStores);

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


//    public List<Item> viewInventory(Long storeId) {
//        return itemRepository.findAllByStoreId(storeId);
//    }

//    public Item requestItemAddition(Long storeId, Item item) {
//        item.setStoreId(storeId);
//        return itemRepository.save(item);
//    }

//    public Item updateItemQuantity(Long storeId, Long itemId, int quantity) throws Exception {
//        return itemRepository.findById(itemId)
//                .map(existingItem -> {
//                    existingItem.setInitialQuantity(quantity);
//                    return itemRepository.save(existingItem);
//                })
//                .orElseThrow(() -> new Exception("Item not found in the given store"));
//    }

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

    // get the stores managed by a user
    public Set<Store> getManagedStores(String email) {
        return userRepository.findByEmail(email)
                .map(User::getManagedStores)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public Set<Store> getManagedStores(Long userId) {
        return userRepository.findById(userId)
                .map(User::getManagedStores)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // add a store to the stores managed by a user, by user id and store id
    public User addManagedStore(Long userId, Long storeId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.addManagedStore(storeRepository.findById(storeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Store not found")));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // remove a store from the stores managed by a user, by user id and store id
    public User removeManagedStore(Long userId, Long storeId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.removeManagedStore(storeRepository.findById(storeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Store not found")));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public void addManagedStore(String email, Store store) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    user.addManagedStore(store);
                    userRepository.save(user);
                });
    }
}
