package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a purchase order in the store management application.
 * Each purchase order has an id, status, associated store, item, and a quantity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne
    private PurchaseOrderLineItem purchaseOrderLineItem;

    private Integer quantity;

    @Getter
    @ManyToOne
    private Item item;

    @Getter
    @ManyToOne
    private Store store;

}
