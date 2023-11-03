package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.PurchaseOrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * PurchaseOrderRepository extends JpaRepository to gain CRUD operations.
 * "PurchaseOrder" is the entity type and "Long" is the ID type.
 */
public interface PurchaseOrderLineItemRepository extends JpaRepository<PurchaseOrderLineItem, Long> {
    Set<PurchaseOrderLineItem> findAllByPurchaseOrderId(Long purchaseOrderId);
}
