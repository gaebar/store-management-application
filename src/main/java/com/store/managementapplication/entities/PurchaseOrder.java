package com.store.managementapplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a purchase order in the store management application.
 * Each purchase order has an id, status, associated store, item, and a quantity.
 */
@Data
@Builder
@NoArgsConstructor
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Set<PurchaseOrderLineItem> purchaseOrderLineItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", referencedColumnName = "id", nullable = false)
    private Store store;

    public PurchaseOrder(Long id, @NonNull String status, Set<PurchaseOrderLineItem> purchaseOrderLineItems, Store store) {
        this.id = id;
        this.status = status;
        this.purchaseOrderLineItems = purchaseOrderLineItems;
        this.store = store;
    }

    public PurchaseOrder(Long id) {
        this.id = id;
    }

    public PurchaseOrder(Long id, String status) {
        this.id = id;
        this.status = status;
    }


    public void addPurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
        purchaseOrderLineItems.add(purchaseOrderLineItem);
    }

    // override tostring method
    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", status='" + status + '\'' +
                // ", purchaseOrderLineItems=" + purchaseOrderLineItems +
                // ", store=" + store +
                '}';
    }
}
