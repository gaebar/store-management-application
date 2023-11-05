package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.StoreInventory;
import com.store.managementapplication.entities.User;
import com.store.managementapplication.services.StoreInventoryService;
import com.store.managementapplication.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
@Tag(name = "Store Inventory", description = "Store Inventory API")
public class StoreInventoryController {

    private final StoreInventoryService storeInventoryService;
    private final UserService userService;

    public StoreInventoryController(StoreInventoryService storeInventoryService, UserService userService) {
        this.storeInventoryService = storeInventoryService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<StoreInventory>> getAllInventories() {
        return ResponseEntity.ok(storeInventoryService.getAllInventories());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<StoreInventory> getInventoryById(@PathVariable Long id) {
        return storeInventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StoreInventory> createInventory(@RequestBody StoreInventory storeInventory) {
        return ResponseEntity.ok(storeInventoryService.saveInventory(storeInventory));
    }

    @PutMapping("/{storeId}/{itemId}/{count}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<StoreInventory> addItemsToInventory(@PathVariable Long storeId, @PathVariable Long itemId, @PathVariable int count) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var roles = auth.getAuthorities();

        // User is admin
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            var response = storeInventoryService.addItemToStoreInventory(storeId, itemId, count, "APPROVED");
            return ResponseEntity.ok(response);
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"))) {
            User user = userService.getUserByEmail(auth.getName());
            user.getManagedStores().stream().
                    filter(s -> s.getId().equals(storeId)).
                    findFirst().orElseThrow(() -> new RuntimeException("User is not authorized to perform this action"));
            return ResponseEntity.ok(storeInventoryService.addItemToStoreInventory(storeId, itemId, count, "PENDING"));
        } else {
            throw new RuntimeException("User with role " + roles + " is not authorized to perform this action");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        storeInventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }

}
