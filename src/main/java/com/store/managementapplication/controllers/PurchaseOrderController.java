package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.services.PurchaseOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    // Only admins and store managers can create a new purchase order;
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @PostMapping("/create")
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        // Calls the service method to create a new purchase order
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

    // Only by admins and store managers can update an existing purchase order
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update/{id}")
    public PurchaseOrder updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder) throws Exception {
        // Calls the service method to update an existing purchase order
        return purchaseOrderService.updatePurchaseOrder(id, purchaseOrder);
    }

    // Only by admins can delete a purchase order;
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deletePurchaseOrder(@PathVariable Long id) throws Exception {
        // Calls the service method to delete a purchase order
        purchaseOrderService.deletePurchaseOrder(id);
    }

    // Only admins can get all purchase orders;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<PurchaseOrder> getAllPurchaseOrders() {
        // Calls the service method to get all purchase orders
        return purchaseOrderService.getAllPurchaseOrders();
    }

    // Only admins and store managers can get a specific purchase order by ID;
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @GetMapping("/{id}")
    public Optional<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        // Calls the service method to get a specific purchase order by its ID
        var order = purchaseOrderService.getPurchaseOrderById(id);
        return order;
    }
}
