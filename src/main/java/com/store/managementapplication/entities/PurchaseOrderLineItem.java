package com.store.managementapplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * Represents a purchase order in the store management application.
 * Each purchase order has an id, status, associated store, item, and a quantity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"itemId", "purchase_order_id"})})

public class PurchaseOrderLineItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", referencedColumnName = "id")
    private Item item;

    @NonNull
    @NotNull
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    @NonNull
    @NotNull
    private PurchaseOrder purchaseOrder;

    public PurchaseOrderLineItem(Item item, int quantity, PurchaseOrder purchaseOrder) {
        this.item = item;
        this.quantity = quantity;
        this.purchaseOrder = purchaseOrder;
    }
}
