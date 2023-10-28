package com.store.managementapplication.services;

import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    // Method to create a new purchase order
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    // Method to update an existing purchase order
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrder.setId(id);
            return purchaseOrderRepository.save(purchaseOrder);
        }
        return null;
    }

    // Method to delete a purchase order
    public void deletePurchaseOrder(Long id) {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
        }
    }

    // Method to get all purchase orders
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    // Method to get a specific purchase order by ID
    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }
}
