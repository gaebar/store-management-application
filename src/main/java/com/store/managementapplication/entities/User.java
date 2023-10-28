package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id // Indicates that the field is a primary key.
    private Long id;
    private String username;
    private String password;
    private String role;

    // Getters and Setters
}