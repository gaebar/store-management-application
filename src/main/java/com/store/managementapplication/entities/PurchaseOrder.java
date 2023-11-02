package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @NonNull
    private String status;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER)
    private Set<PurchaseOrderLineItem> purchaseOrderLineItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storeId", referencedColumnName = "id")
    private Store store;


}
