package com.store.managementapplication.controllers;

import com.store.managementapplication.entities.Item;
import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.entities.PurchaseOrderLineItem;
import com.store.managementapplication.entities.Store;
import com.store.managementapplication.services.PurchaseOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Purchase Order", description = "Purchase Order API")
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
        Optional<PurchaseOrder> order = purchaseOrderService.getPurchaseOrderById(id);
        if (order.isPresent()) {
            Store store = order.get().getStore();
            order.get().setStore(new Store(store.getId()));
            order.get().getPurchaseOrderLineItems().forEach(item -> {
                item.setPurchaseOrder(null);
            });
        }
        return order;
    }

    // Add items to purchase order
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @PutMapping("/addItem/{purchaseOrderId}/{itemId}/{count}")
    public PurchaseOrderLineItem addItemsToPurchaseOrder(@PathVariable Long purchaseOrderId, @PathVariable Long itemId, @PathVariable int count) throws Exception {
        PurchaseOrderLineItem purchaseOrderLineItem = purchaseOrderService.addItemToPurchaseOrder(purchaseOrderId, itemId, count);
        purchaseOrderLineItem.setItem(new Item(purchaseOrderLineItem.getItem().getId()));
        purchaseOrderLineItem.setPurchaseOrder(new PurchaseOrder(purchaseOrderLineItem.getPurchaseOrder().getId()));

        return purchaseOrderLineItem;
    }

    // Get all purchase order line items by purchase order id
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @GetMapping("/getItems/{purchaseOrderId}")
    public Set<PurchaseOrderLineItem> getPurchaseOrderLineItems(@PathVariable Long purchaseOrderId) throws Exception {
        var lineItems = purchaseOrderService.getPurchaseOrderLineItems(purchaseOrderId);
        lineItems.forEach(item -> {
            item.setItem(new Item(item.getItem().getId()));
            item.setPurchaseOrder(new PurchaseOrder(item.getPurchaseOrder().getId(), item.getPurchaseOrder().getStatus()));
        });
        return lineItems;
    }

    // delete purchase order line item
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE_MANAGER')")
    @DeleteMapping("/deleteItem/{itemId}")
    public void deletePurchaseOrderLineItem(@PathVariable Long itemId) throws Exception {
        // Calls the service method to delete a purchase order line item
        purchaseOrderService.deletePurchaseOrderLineItem(itemId);
    }
}