package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

import java.util.Date;

/**
 * Represents a store in the store management application.
 * Each store has an id, name, location, contact information, store type, and opening date.
 */
@Getter
@Entity
public class Store {
    /**
     * -- GETTER --
     *  Returns the id of the store.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * -- GETTER --
     *  Returns the name of the store.
     *
     */
    private String name;
    /**
     * -- GETTER --
     *  Returns the location of the store.
     *
     */
    private String location;
    /**
     * -- GETTER --
     *  Returns the contact information of the store.
     *
     */
    private String contactInformation;
    /**
     * -- GETTER --
     *  Returns the type of the store (e.g., retail, warehouse).
     *
     */
    private String storeType;
    /**
     * -- GETTER --
     *  Returns the opening date of the store.
     *
     */
    private Date openingDate;

    /**
     * Sets the id for the store.
     *
     * @param id the new id for the store.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name for the store.
     *
     * @param name the new name for the store.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the location for the store.
     *
     * @param location the new location for the store.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the contact information for the store.
     *
     * @param contactInformation the new contact information for the store.
     */
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Sets the type for the store.
     *
     * @param storeType the new type for the store.
     */
    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    /**
     * Sets the opening date for the store.
     *
     * @param openingDate the new opening date for the store.
     */
    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
}
