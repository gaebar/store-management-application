package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PurchaseOrder {
    @Id
    private Long id;
    private String status;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Item item;

    private Integer quantity;

    // Getters and Setters
}