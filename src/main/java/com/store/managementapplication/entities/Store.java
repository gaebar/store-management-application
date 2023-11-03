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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "store")
    private Set<StoreInventory> storeInventories = new HashSet<>();

    public Store(Long id) {
        this.id = id;
    }
}
