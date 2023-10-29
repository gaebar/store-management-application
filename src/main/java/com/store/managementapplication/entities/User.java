package com.store.managementapplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Represents a user in the store management application.
 * Each user has an id, username, password, and associated roles.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role",
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}