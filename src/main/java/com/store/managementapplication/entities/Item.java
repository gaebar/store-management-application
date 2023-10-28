package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

import java.util.Objects;

/**
 * Represents an item in the store management application.
 * Each item has an id, name, description, category, price, and an initial quantity.
 */
@Getter
@Entity
public class Item {

    /**
     * -- GETTER --
     *  Returns the id of the item.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * -- GETTER --
     *  Returns the name of the item.
     *
     */
    private String name;
    /**
     * -- GETTER --
     *  Returns the description of the item.
     *
     */
    private String description;
    /**
     * -- GETTER --
     *  Returns the category of the item.
     *
     */
    private String category;
    /**
     * -- GETTER --
     *  Returns the price of the item.
     *
     */
    private Double price;
    /**
     * -- GETTER --
     *  Returns the initial quantity of the item.
     *
     */
    private Integer initialQuantity;

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
    public void setCategory(String category) {
        this.category = category;
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
                '}';
    }
}
