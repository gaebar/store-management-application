package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Represents a store in the store management application.
 * Each store has an id, name, location, contact information, store type, and opening date.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StoreInventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", referencedColumnName = "id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String status;

    public StoreInventory(Store store, Item item, int quantity, String status) {
        this.store = store;
        this.quantity = quantity;
        this.item = item;
        this.status = status;
    }

    public void addItems(int count) {
        this.quantity += count;
    }

    public void removeItems(int count) {
        this.quantity -= count;
    }

    // add status column field request
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Checks if the inventory level is below a certain threshold.
     *
     * @param threshold The threshold to check against.
     * @return True if the quantity is below the threshold, false otherwise.
     */
    public boolean isBelowThreshold(int threshold) {
        return this.quantity < threshold;
    }

}
