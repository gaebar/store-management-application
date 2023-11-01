package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.StoreInventory;
import com.store.managementapplication.services.StoreInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class StoreInventoryController {

    private final StoreInventoryService storeInventoryService;

    public StoreInventoryController(StoreInventoryService storeInventoryService) {
        this.storeInventoryService = storeInventoryService;
    }

    @GetMapping
    public ResponseEntity<List<StoreInventory>> getAllInventories() {
        return ResponseEntity.ok(storeInventoryService.getAllInventories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreInventory> getInventoryById(@PathVariable Long id) {
        return storeInventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StoreInventory> createInventory(@RequestBody StoreInventory storeInventory) {
        return ResponseEntity.ok(storeInventoryService.saveInventory(storeInventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> addItemsToInventory(@PathVariable Long id, @RequestParam int count) {
        storeInventoryService.addItemsToInventory(id, count);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        storeInventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }

}
