package com.store.managementapplication.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an item in the store management application.
 * Each item has an id, name, description, category, price, and an initial quantity.
 * <p>
 * Design Note:
 * <p>
 * A separate "Inventory" class was not included in this design for simplicity.
 * In this model, the inventory is implicitly represented by the items available in a store.
 * Each Item entity has a "quantity" field, indicating the number of units available in the store.
 * <p>
 * In a more complex model, an "Inventory" class could serve as a join between Store and Item,
 * containing additional metadata about each item's presence in a particular store (e.g., quantity, location within the store, etc.).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item implements Serializable {

    // Getter method
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storeId", referencedColumnName = "id")
    private Store store;

    @NonNull
    @Column(unique = true)
    private String name;

    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private ItemCategory category;

    @Positive
    private Double price;

    @Positive
    private Integer initialQuantity;


    @Positive
    private Integer quantity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<PurchaseOrderLineItem> purchaseOrderLineItem = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<StoreInventory> storeInventories = new HashSet<>();

    public void setStoreInventory(StoreInventory storeInventory) {
        this.storeInventories.add(storeInventory);
        storeInventory.setItem(this);
    }


    /**
     * Sets the id for the item.
     *
     * @param id the new id for the item.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name for the item.
     *
     * @param name the new name for the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description for the item.
     *
     * @param description the new description for the item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the category for the item.
     *
     * @param category the new category for the item.
     */
    public void setCategory(ItemCategory category) {
        this.category = category;
    }


    /**
     * Sets the current quantity for the item.
     *
     * @param quantity the new current quantity for the item.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the price for the item.
     *
     * @param price the new price for the item.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Sets the initial quantity for the item.
     *
     * @param initialQuantity the new initial quantity for the item.
     */
    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", initialQuantity=" + initialQuantity +
                ", quantity=" + quantity +
                '}';
    }
}