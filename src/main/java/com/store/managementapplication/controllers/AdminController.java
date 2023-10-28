package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Store;
import com.store.managementapplication.entities.Role;
import com.store.managementapplication.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create a new store
    @PostMapping("/createStore")
    public Store createStore(@RequestBody Store store) {
        return adminService.createStore(store);
    }

    // Update an existing store
    @PutMapping("/updateStore/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store store) {
        return adminService.updateStore(id, store);
    }

    // Delete a store
    @DeleteMapping("/deleteStore/{id}")
    public void deleteStore(@PathVariable Long id) {
        adminService.deleteStore(id);
    }

    // Create a new role
    @PostMapping("/createRole")
    public Role createRole(@RequestBody Role role) {
        return adminService.createRole(role);
    }

    // Update an existing role
    @PutMapping("/updateRole/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return adminService.updateRole(id, role);
    }

    // Delete a role
    @DeleteMapping("/deleteRole/{id}")
    public void deleteRole(@PathVariable Long id) {
        adminService.deleteRole(id);
    }

}
