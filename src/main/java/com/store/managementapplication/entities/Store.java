package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a store in the store management application.
 * Each store has an id, name, location, contact information, store type, and opening date.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String contactInformation;

    @NonNull
    private String storeType;

    @NonNull
    private Date openingDate;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<StoreInventory> storeInventories = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();


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
