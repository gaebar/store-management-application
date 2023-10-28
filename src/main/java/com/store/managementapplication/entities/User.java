package com.store.managementapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import lombok.Getter;

import java.util.Set;

/**
 * Represents a user in the store management application.
 * Each user has an id, username, password, and associated roles.
 */
@Getter
@Entity
public class User {
    /**
     * -- GETTER --
     *  Returns the id of the user.
     *
     */
    @Id
    private Long id;
    /**
     * -- GETTER --
     *  Returns the username of the user.
     *
     */
    private String username;
    /**
     * -- GETTER --
     *  Returns the password of the user.
     *
     */
    private String password;

    /**
     * Represents the roles associated with the user.
     * Uses a many-to-many relationship as a user can have multiple roles,
     * and each role can be associated with multiple users.
     * -- GETTER --
     *  Returns the set of roles associated with the user.
     *

     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /**
     * Sets the id for the user.
     *
     * @param id the new id for the user.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the username for the user.
     *
     * @param username the new username for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the roles for the user.
     *
     * @param roles the new set of roles for the user.
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
