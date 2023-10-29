package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

/**
 * Represents a purchase order in the store management application.
 * Each purchase order has an id, status, associated store, item, and a quantity.
 */
@Getter
@Entity
public class PurchaseOrder {

    /**
     * -- GETTER --
     *  Returns the id of the purchase order.
     *  This is the primary key for the PurchaseOrder entity.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * -- GETTER --
     *  Returns the status of the purchase order.
     *
     */
    private String status;

    /**
     * -- GETTER --
     *  Returns the store associated with the purchase order.
     *
     */
    @ManyToOne
    private Store store;

    /**
     * -- GETTER --
     *  Returns the item associated with the purchase order.
     *
     */
    @ManyToOne
    private Item item;

    /**
     * -- GETTER --
     *  Returns the quantity of the item in the purchase order.
     *
     */
    private Integer quantity;

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
}
