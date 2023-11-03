package com.store.managementapplication.services;

import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.entities.PurchaseOrderLineItem;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import com.store.managementapplication.repositories.ItemRepository;
import com.store.managementapplication.repositories.PurchaseOrderLineItemRepository;
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
    private final PurchaseOrderLineItemRepository puchaseOrderLineItemRepository;


    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, ItemRepository itemRepository, StoreRepository storeRepository, PurchaseOrderLineItemRepository puchaseOrderLineItemRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
        this.puchaseOrderLineItemRepository = puchaseOrderLineItemRepository;
    }

    // Create a new Purchase Order
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
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

    // Add an item to a purchase order, with a specified quantity
    public PurchaseOrder addItemToPurchaseOrder(Long purchaseOrderId, Long itemId, int quantity) throws ResourceNotFoundException {
        var purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));
        var item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));


        PurchaseOrderLineItem lineItem = new PurchaseOrderLineItem();
        lineItem.setItem(item);
        lineItem.setQuantity(quantity);
        lineItem.setPurchaseOrder(null);
        purchaseOrder.addPurchaseOrderLineItem(lineItem);
        puchaseOrderLineItemRepository.save(lineItem);

        //save purchase order line item
        return purchaseOrder;
    }
}