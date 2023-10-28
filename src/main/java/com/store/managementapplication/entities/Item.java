package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer initialQuantity;

    // Getter and Setter
}
