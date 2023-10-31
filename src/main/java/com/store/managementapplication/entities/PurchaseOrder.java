package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Store store;

    @ManyToOne
    private Item item;

    private Integer quantity;
    
    private String createdBy;

    private String updatedBy;


    /**
     * Sets the id for the purchase order.
     *
     * @param id the new id for the purchase order.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the status for the purchase order.
     *
     * @param status the new status for the purchase order.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the store for the purchase order.
     *
     * @param store the new store for the purchase order.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Sets the item for the purchase order.
     *
     * @param item the new item for the purchase order.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Sets the quantity for the item in the purchase order.
     *
     * @param quantity the new quantity for the item in the purchase order.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the created by user/role for the purchase order.
     *
     * @param createdBy the user or role who created the purchase order.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the updated by user/role for the purchase order.
     *
     * @param updatedBy the user or role who last updated the purchase order.
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
