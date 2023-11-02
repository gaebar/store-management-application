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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storeId", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    /**
     * Adds items to the inventory.
     *
     * @param count The number of items to add.
     */
    public void addItems(int count) {
        this.quantity += count;
    }

    /**
     * Removes items from the inventory.
     *
     * @param count The number of items to remove.
     * @throws IllegalArgumentException if count is greater than the current quantity.
     */
    public void removeItems(int count) {
        if (count > this.quantity) {
            throw new IllegalArgumentException("Cannot remove more items than available in inventory.");
        }
        this.quantity -= count;
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
