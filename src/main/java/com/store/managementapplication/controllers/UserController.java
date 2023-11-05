package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user (Admin role)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Update an existing user (Admin role)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Get user by id (Admin role)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        user.setPassword("");
        return user;
    }

    // Delete a user (Admin role)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Add store to a user (Admin role)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addManagedStore/{userId}/{storeId}")
    public User addManagedStore(@PathVariable Long userId, @PathVariable Long storeId) {
        return userService.addManagedStore(userId, storeId);
    }

    // Remove store from a user (Admin role)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/removeManagedStore/{userId}/{storeId}")
    public User removeManagedStore(@PathVariable Long userId, @PathVariable Long storeId) {
        return userService.removeManagedStore(userId, storeId);
    }

    // Retrieve the stores managed by the authenticated store manager
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/managedStores/{userId}")
    public Set<Store> getManagedStores(@PathVariable Long userId) {
        return userService.getManagedStores(userId);
    }

//    // View inventory for a specific store (Store Staff role)
//    @PreAuthorize("hasRole('STORE_STAFF')")
//    @GetMapping("/inventory/{storeId}")
//    public List<Item> viewInventory(@PathVariable Long storeId) {
//        return userService.viewInventory(storeId);
//    }

    // Request item additions for a specific store (Store Staff role)
//    @PreAuthorize("hasRole('STORE_STAFF')")
//    @PostMapping("/requestAddition/{storeId}")
//    public Item requestItemAddition(@PathVariable Long storeId, @RequestBody Item item) {®
//        return userService.requestItemAddition(storeId, item);
//    }

    // Update item quantities for a specific store (Store Staff role)
//    @PreAuthorize("hasRole('STORE_STAFF')")
//    @PutMapping("/updateQuantity/{storeId}/{itemId}/{quantity}")
//    public Item updateItemQuantity(@PathVariable Long storeId, @PathVariable Long itemId, @PathVariable int quantity) throws Exception {
//        return userService.updateItemQuantity(storeId, itemId, quantity);
//    }
}
