package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * Represents a user role in the store management application.
 * Each role has an id and a name which denotes the permissions and responsibilities.
 */
@Getter
@Entity
public class Role {

    /**
     * -- GETTER --
     *  Returns the id of the role.
     * This is the primary key for the Role entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * -- GETTER --
     *  Returns the name of the role.
     *
     */
    private String name;

    /**
     * Sets the id for the role.
     *
     * @param id the new id for the role.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name for the role.
     *
     * @param name the new name for the role.
     */
    public void setName(String name) {
        this.name = name;
    }
}
