package com.store.managementapplication.services;

import com.store.managementapplication.entities.PurchaseOrder;
import com.store.managementapplication.repositories.PurchaseOrderRepository;
import com.store.managementapplication.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    /**
     * Create a new purchase order.
     * @param purchaseOrder The purchase order to create.
     * @return The created purchase order.
     */
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }



    /**
     * Update an existing purchase order.
     * @param id The ID of the purchase order to update.
     * @param purchaseOrder The new purchase order data.
     * @return The updated purchase order.
     * @throws ResourceNotFoundException if the purchase order is not found.
     */
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) throws ResourceNotFoundException {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrder.setId(id);
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new ResourceNotFoundException("Purchase order not found");
        }
    }

    /**
     * Delete an existing purchase order.
     * @param id The ID of the purchase order to delete.
     * @throws ResourceNotFoundException if the purchase order is not found.
     */
    public void deletePurchaseOrder(Long id) throws ResourceNotFoundException {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Purchase order not found");
        }
    }

    /**
     * Get all purchase orders.
     * @return A list of all purchase orders.
     */
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    /**
     * Get a specific purchase order by ID.
     * @param id The ID of the purchase order to retrieve.
     * @return The purchase order, if found.
     */
    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }
}
