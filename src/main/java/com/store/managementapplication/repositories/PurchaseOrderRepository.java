package com.store.managementapplication.repositories;

import com.store.managementapplication.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository extends JpaRepository to gain CRUD operations.
 * "Role" is the entity type and "Long" is the ID type.
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}