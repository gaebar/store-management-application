package com.store.managementapplication.services;

import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.ItemRepository;
import com.store.managementapplication.repositories.PurchaseOrderRepository;
import com.store.managementapplication.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, ItemRepository itemRepository, StoreRepository storeRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
    }

    // Create a new Purchase Order
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        // Check if Item and Store exist
        if (itemRepository.existsById(purchaseOrder.getItem().getId()) &&
                storeRepository.existsById(purchaseOrder.getStore().getId())) {

            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new ResourceNotFoundException("Item or Store not found");
        }
    }

    // Update an existing Purchase Order
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) throws ResourceNotFoundException {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrder.setId(id);
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new ResourceNotFoundException("Purchase Order not found");
        }
    }

    // Delete a Purchase Order by its ID
    public void deletePurchaseOrder(Long id) throws ResourceNotFoundException {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Purchase Order not found");
        }
    }

    // Get all Purchase Orders
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    // Get a specific Purchase Order by ID
    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }
}