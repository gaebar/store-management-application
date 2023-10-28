package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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

    // Delete a user (Admin role)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // View inventory for a specific store (Store Staff role)
    @PreAuthorize("hasRole('STORE_STAFF')")
    @GetMapping("/inventory/{storeId}")
    public List<Item> viewInventory(@PathVariable Long storeId) {
        return userService.viewInventory(storeId);
    }

    // Request item additions for a specific store (Store Staff role)
    @PreAuthorize("hasRole('STORE_STAFF')")
    @PostMapping("/requestAddition/{storeId}")
    public Item requestItemAddition(@PathVariable Long storeId, @RequestBody Item item) {
        return userService.requestItemAddition(storeId, item);
    }

    // Update item quantities for a specific store (Store Staff role)
    @PreAuthorize("hasRole('STORE_STAFF')")
    @PutMapping("/updateQuantity/{storeId}/{itemId}/{quantity}")
    public Item updateItemQuantity(@PathVariable Long storeId, @PathVariable Long itemId, @PathVariable int quantity) {
        return userService.updateItemQuantity(storeId, itemId, quantity);
    }
}
