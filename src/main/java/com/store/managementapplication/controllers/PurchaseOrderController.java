package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // Only admins and store managers can create a new purchase order
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @PostMapping("/create")
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

    // Only admins and store managers can update an existing purchase order
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @PutMapping("/update/{id}")
    public PurchaseOrder updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder) throws Exception {
        return purchaseOrderService.updatePurchaseOrder(id, purchaseOrder);
    }

    // Only admins can delete a purchase order
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deletePurchaseOrder(@PathVariable Long id) throws Exception {
        purchaseOrderService.deletePurchaseOrder(id);
    }

    // Only admins can get all purchase orders
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    // Admins and store managers can get a specific purchase order by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @GetMapping("/get/{id}")
    public Optional<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }
}
