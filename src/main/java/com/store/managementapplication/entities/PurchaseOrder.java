package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a purchase order in the store management application.
 * Each purchase order has an id, status, associated store, item, and a quantity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchaseOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String status;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderLineItem> purchaseOrderLineItems;

    @ManyToOne
    private Store store;
}
