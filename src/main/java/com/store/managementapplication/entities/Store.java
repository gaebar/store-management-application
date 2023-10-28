package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Store {
    @Id
    private Long Id;
    private  String name;
    private String location;
    private String contactInformation;
    private String storeType;
    private Date openingDate;

    // Getters and Setters

}
