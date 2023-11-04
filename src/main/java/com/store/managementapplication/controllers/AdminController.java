package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Role;
import com.store.managementapplication.entities.Store;
import com.store.managementapplication.services.RoleService;
import com.store.managementapplication.services.StoreService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin", description = "Admin API")
public class AdminController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private RoleService roleService;

    // Only Admin can create a new store
    @PostMapping("/createStore")
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }

    // Only Admin can update an existing store
    @PutMapping("/updateStore/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody Store store) throws Exception {
        return storeService.updateStore(id, store);
    }

    // Only Admin can delete a store
    @DeleteMapping("/deleteStore/{id}")
    public void deleteStore(@PathVariable Long id) throws Exception {
        storeService.deleteStore(id);
    }

    // Only Admin can create a new role
    @PostMapping("/createRole")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    // Only Admin can update an existing role
    @PutMapping("/updateRole/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) throws Exception {
        return roleService.updateRole(id, role);
    }

    // Only Admin can delete a role
    @DeleteMapping("/deleteRole/{id}")
    public void deleteRole(@PathVariable Long id) throws Exception {
        roleService.deleteRole(id);
    }
}
